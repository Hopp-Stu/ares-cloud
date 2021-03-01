package com.ares.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 * @author 1065569578@qq.com
 */

@SpringBootApplication
@EnableDiscoveryClient
public class AresGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AresGateWayApplication.class, args);
    }

}
