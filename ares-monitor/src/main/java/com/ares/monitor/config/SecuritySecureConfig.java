package com.ares.monitor.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

/**
 * @description:
 * @author: yy
 * @date: 2020/09/16
 * @see: com.ares.admin.config SecuritySecureConfig.java
 **/
@Configuration(proxyBeanMethods = false)
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

    private final AdminServerProperties adminServerProperties;

    @Value("${ares.admin.username}")
    private String username;
    @Value("${ares.admin.password}")
    private String password;

    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
        this.adminServerProperties = adminServerProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminServerProperties.path("/"));

        http.authorizeRequests((authorizeRequests) -> authorizeRequests
                .antMatchers(adminServerProperties.path("/assets/**")).permitAll()
                .antMatchers(adminServerProperties.path("/static/**")).permitAll()
                .antMatchers(adminServerProperties.path("/login")).permitAll()
                .anyRequest().authenticated()).formLogin((formLogin) -> formLogin.loginPage(adminServerProperties.path("/login")).successHandler(successHandler).and()
        ).logout((logout) -> logout.logoutUrl(adminServerProperties.path("/logout"))).httpBasic(Customizer.withDefaults())
                .csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(new AntPathRequestMatcher(adminServerProperties.path("/instances"),
                                        HttpMethod.POST.toString()),
                                new AntPathRequestMatcher(adminServerProperties.path("/instances/*"),
                                        HttpMethod.DELETE.toString()),
                                new AntPathRequestMatcher(adminServerProperties.path("/actuator/**"))
                        ))
                .rememberMe((rememberMe) -> rememberMe.key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(username).password("{noop}" + password).roles("USER");
    }


}
