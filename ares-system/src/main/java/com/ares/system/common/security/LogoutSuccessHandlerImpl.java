package com.ares.system.common.security;

import com.alibaba.fastjson.JSON;
import com.ares.core.model.base.BaseResult;
import com.ares.core.model.base.Constants;
import com.ares.system.client.RedisClient;
import com.ares.system.common.jwt.JwtTokenUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/19
 * @see: com.ares.system.common.security LogoutSuccessHandlerImpl.java
 **/
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Resource
    RedisClient redisClient;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String token = JwtTokenUtils.getToken(httpServletRequest);
        if (null != token) {
            String userName = JwtTokenUtils.getUsernameFromToken(token);
            BaseResult hasKey = redisClient.hasKey(Constants.LOGIN_INFO + userName);
            if (Boolean.parseBoolean(String.valueOf(hasKey.get("data")))) {
                redisClient.delCache(Constants.LOGIN_INFO + userName);
            }
            redisClient.delCache(token);
        }
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter().print(JSON.toJSONString(BaseResult.success()));

    }
}
