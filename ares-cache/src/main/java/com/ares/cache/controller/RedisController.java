package com.ares.cache.controller;

import com.ares.cache.service.RedisService;
import com.ares.core.model.base.AjaxResult;
import com.ares.core.model.cache.CacheBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/22
 * @see: com.ares.cache.controller RedisController.java
 **/
@RestController
@RequestMapping("/redis/*")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping("setCache")
    public AjaxResult set(@RequestBody CacheBody body) {
        redisService.set(body.getKey(), body.getValue(), body.getTime());
        return AjaxResult.success();
    }

    /**
     * 根据key获取数据
     *
     * @param key
     * @return
     */
    @GetMapping("getCache/{key}")
    public AjaxResult get(@PathVariable String key) {
        return AjaxResult.successData(redisService.get(key));
    }

    /**
     * 删除操作
     *
     * @param keys
     */
    @DeleteMapping("delCache/{keys}")
    public AjaxResult del(@PathVariable String keys) {
        redisService.del(keys);
        return AjaxResult.success();
    }


    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    @GetMapping("hasKey/{key}")
    public AjaxResult hasKey(@PathVariable String key) {
        return AjaxResult.successData(redisService.hasKey(key));
    }

    /**
     * 获取 hash 数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    @PostMapping("getHash/{key}/{hashKey}")
    public AjaxResult getHash(@PathVariable String key, @PathVariable String hashKey) {
        return AjaxResult.successData(redisService.getHash(key, hashKey));
    }

    /**
     * set hash 数据
     *
     * @return
     */
    @PostMapping("setHash")
    public AjaxResult setHash(@RequestBody CacheBody body) {
        return AjaxResult.successData(redisService.setHash(body.getKey(), body.getHashKey(), body.getValue()));
    }

    /**
     * 删除hash数据
     *
     * @return
     */
    @PostMapping("delHash")
    public AjaxResult delHash(@RequestBody CacheBody body) {
        return AjaxResult.successData(redisService.delHash(body.getKey(), body.getHashKeys()));
    }

    @PostMapping("hasHash")
    public AjaxResult hasHash(@RequestBody CacheBody body) {
        return AjaxResult.successData(redisService.hasHash(body.getKey(), body.getHashKey()));
    }

    @PostMapping("expire")
    public AjaxResult expire(@RequestBody CacheBody body) {
        return AjaxResult.successData(redisService.expire(body.getKey(), body.getTime()));
    }

    @GetMapping("getKeysByPattern/{pattern}")
    public AjaxResult getKeysByPattern(@PathVariable String pattern) {
        return AjaxResult.successData(redisService.getKeysByPattern(pattern));
    }

}
