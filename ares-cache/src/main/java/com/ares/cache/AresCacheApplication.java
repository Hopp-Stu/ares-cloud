package com.ares.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/22
 * @see: com.ares.cache AresCacheApplication.java
 **/
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AresCacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(AresCacheApplication.class, args);
    }
}
