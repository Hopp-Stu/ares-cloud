package com.ares.system.utils;

import com.alibaba.fastjson.JSON;
import com.ares.core.model.base.AjaxResult;
import com.ares.core.utils.SpringUtils;
import com.ares.system.client.RedisClient;
import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

/**
 * @description:
 * @author: yy 2020/05/15
 **/
public class AresCommonUtils {
    private AresCommonUtils() {
    }

    public static boolean isNotEmpty(Object obj) {
        if (obj instanceof Map) {
            return ((Map) obj).size() > 0;
        } else if (obj instanceof Collection) {
            return !((Collection) obj).isEmpty();
        } else if (obj instanceof String) {
            return !"".equals(((String) obj).trim());
        } else {
            return null != obj;
        }
    }

    public static boolean isEmpty(Object obj) {
        return !isNotEmpty(obj);
    }


    public static String getCode(HttpServletRequest request, String key) {
        try {
            String code = request.getParameter(key);
            if (null != code) {
                code = code.trim();
            }
            if ("".equals(code)) {
                code = null;
            }
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验验证码是否有效
     *
     * @param code
     * @param uuid
     * @return
     */
    public static boolean checkVerifyCode(String code, String uuid) {
        RedisClient redisClient = SpringUtils.getBean(RedisClient.class);
        AjaxResult verifyCodeActual = redisClient.getCache(Constants.KAPTCHA_SESSION_KEY + uuid);
        if (null == verifyCodeActual || !verifyCodeActual.get("data").equals(code)) {
            return false;
        }
        return true;
    }

    /**
     * 回写请求数据
     *
     * @param response
     * @param result
     * @throws IOException
     */
    public static void writeResponse(HttpServletResponse response, Object result) throws IOException {
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JSON.toJSONString(result));
    }

    /**
     * 远程请求调用
     *
     * @param uri
     * @param data
     * @param contentType
     * @return
     * @throws IOException
     */
    public static String post(String uri, String data, String contentType) throws IOException {
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", contentType);
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setConnectTimeout(300000);
        connection.connect();

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.write(data.getBytes());
        out.flush();
        out.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String lines;
        while (null != (lines = reader.readLine())) {
            lines = new String(lines.getBytes());
            sb.append(lines);
        }

        reader.close();
        connection.disconnect();
        return sb.toString();
    }
}
