package com.ares.system.common.security;

import com.ares.core.model.base.AjaxResult;
import com.ares.core.model.base.Constants;
import com.ares.core.utils.SpringUtils;
import com.ares.system.client.RedisClient;
import com.ares.system.common.jwt.JwtTokenUtils;
import com.ares.system.utils.AresCommonUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

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

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String token = JwtTokenUtils.getToken(httpServletRequest);
        if (null != token) {
            RedisClient redisClient = SpringUtils.getBean(RedisClient.class);
            String userName = JwtTokenUtils.getUsernameFromToken(token);
            AjaxResult result = redisClient.hasKey(Constants.LOGIN_INFO + userName);
            if (Boolean.parseBoolean(String.valueOf(result.get("data")))) {
                redisClient.delCache(Constants.LOGIN_INFO + userName);
            }
        }
        AresCommonUtils.writeResponse(httpServletResponse, AjaxResult.success());
    }
}
