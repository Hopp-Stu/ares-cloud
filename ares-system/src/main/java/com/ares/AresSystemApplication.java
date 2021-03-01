package com.ares;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/19
 * @see: com.ares.system AresSystemApplication.java
 **/
@MapperScan("com.ares.**.dao")
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class AresSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(AresSystemApplication.class, args);
        System.out.println("         ______                                     __    __ ");
        System.out.println("		/      \\                                   /  |  /  |");
        System.out.println("       /$$$$$$  |  ______   _____  ____    ______  $$ | _$$ |_     _____");
        System.out.println("       $$ |  $$/  /      \\ /     \\/   \\  /     \\   $$ |/ $$   |   /     \\");
        System.out.println("       $$ |      /$$$$$$  |$$$$$$ $$$$  |/$$$$$$  |$$ |$$$$$$/   /$$$$$$ |");
        System.out.println("	   $$ |   __ $$ |  $$ |$$ | $$ | $$ |$$ |  $$ |$$ |  $$ | __ $$    $$|");
        System.out.println("       $$ \\__/  |$$ \\__$$ |$$ | $$ | $$ |$$ |__$$ |$$ |  $$ |/  |$$$$$$$$/");
        System.out.println("	   $$    $$/ $$    $$/ $$ | $$ | $$ |$$    $$/ $$ |  $$  $$/ $$      |");
        System.out.println("	   $$$$$$/    $$$$$$/  $$/  $$/  $$/ $$$$$$$/  $$/    $$$$/   $$$$$$$/");
        System.out.println("				                         $$ |");
        System.out.println("		                                 $$ |");
        System.out.println("		                                 $$/");
    }
}
