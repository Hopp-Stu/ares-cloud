package com.ares.gateway.model;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/21
 * @see: com.ares.gateway.model GatewayFilterDefinition.java
 **/
@Data
public class GatewayFilterDefinition {
    /**
     * Filter Name
     */
    private String name;
    /**
     * 对应的路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();
}
