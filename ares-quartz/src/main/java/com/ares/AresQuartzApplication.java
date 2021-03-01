package com.ares;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/22
 * @see: com.ares.quartz AresQuartzApplication.java
 **/

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.ares.**.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class AresQuartzApplication {
    public static void main(String[] args) {
        SpringApplication.run(AresQuartzApplication.class, args);
    }
}
