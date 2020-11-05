package com.ares.system.common.security;

import com.alibaba.fastjson.JSON;
import com.ares.core.model.base.BaseResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/20
 * @see: com.ares.system.common.security AuthenticationEntryPointImpl.java
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter().print(JSON.toJSONString(BaseResult.unAuth()));
    }
}
