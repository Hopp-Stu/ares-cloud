package com.ares.system.common.jwt;

import com.alibaba.fastjson.JSON;
import com.ares.core.model.base.BaseResult;
import com.ares.system.common.security.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/19
 * @see: com.ares.system.common.jwt JwtAuthenticationFilter.java
 **/
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取token, 并检查登录状态
        try {
            SecurityUtils.checkAuthentication(request);
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(JSON.toJSONString(BaseResult.unLogin()));
        }
    }
}
