package com.ares.core.model.cache;

import lombok.Data;

import java.util.Collection;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/22
 * @see: com.ares.core.model.cache CacheBody.java
 **/
@Data
public class CacheBody {

    private String key;

    private String hashKey;

    private Object value;

    private long time;

    private Collection<String> keys;

    private String keysArray;

    private Object hashKeys;
}
