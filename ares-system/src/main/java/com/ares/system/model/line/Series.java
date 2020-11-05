package com.ares.system.model.line;

import lombok.Data;

import java.util.Map;

/**
 * @description:
 * @author: yy
 * @date: 2020/09/14
 * @see: com.ares.system.model.line Series.java
 **/
@Data
public class Series {
    private String name;
    private Map<String, Object> itemStyle;
    private boolean smooth;
    private String type;
    private Number[] data;
    private Number animationDuration;
    private String animationEasing;
}
