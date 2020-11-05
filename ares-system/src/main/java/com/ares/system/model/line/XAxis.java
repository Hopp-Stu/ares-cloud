package com.ares.system.model.line;

import lombok.Data;

import java.util.Map;

/**
 * @description:
 * @author: yy
 * @date: 2020/09/14
 * @see: com.ares.system.model.line XAxis.java
 **/
@Data
public class XAxis {
    private String[] data;
    private boolean boundaryGap;
    private Map<String,Object> axisTick;
}
