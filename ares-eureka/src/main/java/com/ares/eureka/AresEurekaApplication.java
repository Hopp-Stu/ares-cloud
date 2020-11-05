package com.ares.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 1065569578@qq.com
 */
@EnableEurekaServer
@SpringBootApplication
public class AresEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AresEurekaApplication.class, args);
    }

}
