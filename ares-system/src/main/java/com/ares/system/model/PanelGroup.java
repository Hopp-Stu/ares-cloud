package com.ares.system.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: yy
 * @date: 2020/09/14
 * @see: com.ares.system.model PanelGroup.java
 **/
@Data
public class PanelGroup implements Serializable {
    private String name;
    private String type;
    private String icon;
    private Number startVal;
    private Number endVal;
    private Number duration;
}
