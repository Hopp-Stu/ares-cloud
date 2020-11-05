package com.ares.gateway.model;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/21
 * @see: com.ares.gateway.model GatewayPredicateDefinition.java
 **/
@Data
public class GatewayPredicateDefinition {
    /**
     * 断言对应的Name
     */
    private String name;
    /**
     * 配置的断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();
}
