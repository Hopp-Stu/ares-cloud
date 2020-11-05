package com.ares.cache.controller;

import com.ares.cache.service.RedisService;
import com.ares.core.model.base.BaseResult;
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
    public BaseResult set(@RequestBody CacheBody body) {
        redisService.set(body.getKey(), body.getValue(), body.getTime());
        return BaseResult.success();
    }

    /**
     * 根据key获取数据
     *
     * @param key
     * @return
     */
    @GetMapping("getCache/{key}")
    public BaseResult get(@PathVariable String key) {
        redisService.get(key);
        return BaseResult.success();
    }

    /**
     * 删除操作
     *
     * @param keys
     */
    @DeleteMapping("delCache/{keys}")
    public BaseResult del(@PathVariable String keys) {
        redisService.del(keys);
        return BaseResult.success();
    }


    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    @GetMapping("hasKey/{key}")
    public BaseResult hasKey(@PathVariable String key) {
        return BaseResult.successData(redisService.hasKey(key));
    }

    /**
     * 获取 hash 数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    @PostMapping("getHash/{key}/{hashKey}")
    public BaseResult getHash(@PathVariable String key, @PathVariable String hashKey) {
        return BaseResult.successData(redisService.getHash(key, hashKey));
    }

    /**
     * set hash 数据
     *
     * @return
     */
    @PostMapping("setHash")
    public BaseResult setHash(@RequestBody CacheBody body) {
        return BaseResult.successData(redisService.setHash(body.getKey(), body.getHashKey(), body.getValue()));
    }

    /**
     * 删除hash数据
     *
     * @return
     */
    @PostMapping("delHash")
    public BaseResult delHash(@RequestBody CacheBody body) {
        return BaseResult.successData(redisService.delHash(body.getKey(), body.getHashKeys()));
    }

    @PostMapping("hasHash")
    public BaseResult hasHash(@RequestBody CacheBody body) {
        return BaseResult.successData(redisService.hasHash(body.getKey(), body.getHashKey()));
    }

    @PostMapping("expire")
    public BaseResult expire(@RequestBody CacheBody body) {
        return BaseResult.successData(redisService.expire(body.getKey(), body.getTime()));
    }

    @GetMapping("getKeysByPattern/{pattern}")
    public BaseResult getKeysByPattern(@PathVariable String pattern) {
        return BaseResult.successData(redisService.getKeysByPattern(pattern));
    }

}
