package com.ares.system.client;

import com.ares.core.model.base.AjaxResult;
import com.ares.core.model.cache.CacheBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/22
 * @see: com.ares.system.client RedisClient.java
 **/
@FeignClient(value = "Ares-Cache")
public interface RedisClient {

    @RequestMapping(value = "/cache/redis/setCache",method = RequestMethod.POST)
    public AjaxResult setCache(@RequestBody CacheBody body);

    @RequestMapping(value = "/cache/redis/getCache/{key}",method = RequestMethod.GET)
    public AjaxResult getCache(@PathVariable("key") String key);

    @RequestMapping(value = "/cache/redis/delCache/{keys}",method = RequestMethod.DELETE)
    public AjaxResult delCache(@PathVariable("keys") String keys) ;

    @RequestMapping(value = "/cache/redis/hasKey/{key}",method = RequestMethod.GET)
    public AjaxResult hasKey(@PathVariable("key") String key);

    @RequestMapping(value = "/cache/redis/getKeysByPattern/{pattern}",method = RequestMethod.GET)
    public AjaxResult getKeysByPattern(@PathVariable("pattern") String pattern);
}
