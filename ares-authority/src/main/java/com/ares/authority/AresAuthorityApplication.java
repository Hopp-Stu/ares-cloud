package com.ares.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 *
 * @author 1065569578@qq.com
 */

@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
public class AresAuthorityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AresAuthorityApplication.class, args);
    }

}
