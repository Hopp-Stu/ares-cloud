package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/22
 * @see: com.example.demo.controller Demo1Controller.java
 **/
@RestController
@RequestMapping("/demo1")
public class Demo1Controller {

    @Value("${server.port}")
    private Integer port;

    @Value("${spring.application.name}")
    private String name;

    @RequestMapping("/info")
    public String demo() {
        return "this is " + name + " at port:" + port;
    }
}
