package com.ares.cache.service;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/22
 * @see: com.ares.cache.service RedisService.java
 **/

import com.ares.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private static Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public boolean set(String key, Object value, long expire) {
        try {
            if (expire > 0) {
                redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            logger.error("redis set {} error ,message {}", key, StringUtils.substring(e.toString(), 0, 2000));
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key获取数据
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除操作
     *
     * @param keys
     */
    public void del(String... keys) {
        if (null != keys && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(keys));
            }
        }
    }

    /**
     * 删除操作
     *
     * @param keys
     */
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            logger.error("find {} error,message {}", key, StringUtils.substring(e.toString(), 0, 2000));
            return false;
        }
    }

    /**
     * 获取 hash 数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Object getHash(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * set hash 数据
     *
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    public boolean setHash(String key, Object hashKey, Object value) {
        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
            return true;
        } catch (Exception e) {
            logger.error("setHash key:{} error,message:{}", key, StringUtils.substring(e.toString(), 0, 2000));
            return false;
        }
    }

    /**
     * 删除hash数据
     *
     * @param key
     * @param hashKeys
     * @return
     */
    public boolean delHash(String key, Object... hashKeys) {
        try {
            redisTemplate.opsForHash().delete(key, hashKeys);
            return true;
        } catch (Exception e) {
            logger.error("delHash key:{} error,message:{}", key, StringUtils.substring(e.toString(), 0, 2000));
            return false;
        }
    }

    public boolean hasHash(String key, Object hashKey) {
        try {
            redisTemplate.opsForHash().hasKey(key, hashKey);
            return true;
        } catch (Exception e) {
            logger.error("hasHash key:{} error,message:{}", key, StringUtils.substring(e.toString(), 0, 2000));
            return false;
        }
    }

    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            logger.error("expire key:{} error,message:{}", key, StringUtils.substring(e.toString(), 0, 2000));
            return false;
        }
    }

    public Set<String> getKeysByPattern(String pattern) {
        return redisTemplate.keys(pattern);
    }

}
