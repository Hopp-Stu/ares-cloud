package com.ares.system.utils;

import com.alibaba.fastjson.JSONObject;
import com.ares.core.model.base.BaseResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/19
 * @see: com.ares.system.utils HttpUtils.java
 **/
public class HttpUtils {
    /**
     * 获取HttpServletRequest对象
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 输出信息到浏览器
     *
     * @param response
     * @param data
     * @throws IOException
     */
    public static void write(HttpServletResponse response, Object data) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        BaseResult result = BaseResult.successData(data);
        String json = JSONObject.toJSONString(result);
        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
