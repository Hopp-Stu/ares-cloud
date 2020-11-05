package com.ares.quartz;

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
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.ares.**.dao")
@ComponentScan({"com.ares"})
@EnableDiscoveryClient
@SpringBootApplication
public class AresQuartzApplication {
    public static void main(String[] args) {
        SpringApplication.run(AresQuartzApplication.class, args);
    }
}
