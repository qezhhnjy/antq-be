package com.qezhhnjy.antq.web.util;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhaoyangfu
 * @date 2021/6/8-23:39
 */
@Component
@SuppressWarnings("unchecked")
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    public <T> void set(String key, T value) {
        redisTemplate.opsForValue().set(key, (Serializable) value);
    }

    public <T> T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    // ======================================================

    public Long listLeftPush(String key, Serializable value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    public <T> T listRightPop(String key) {
        return (T) redisTemplate.opsForList().rightPop(key);
    }

    public <T> List<T> list(String key) {
        return (List<T>) redisTemplate.opsForList().range(key, 0, -1);
    }

    // ======================================================

    public void hashPut(String key, Serializable hashKey, Serializable value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public <T> T hashGet(String key, Serializable hashKey) {
        return (T) redisTemplate.opsForHash().get(key, hashKey);
    }

    public Map<Object, Object> hash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    // ======================================================

    public <T> Set<T> setGet(String key) {
        return (Set<T>) redisTemplate.opsForSet().members(key);
    }

    public Long setPut(String key, Serializable... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    public <T> T setPop(String key) {
        return (T) redisTemplate.opsForSet().pop(key);
    }

    // ======================================================

    public <T> Set<T> zSet(String key) {
        return (Set<T>) redisTemplate.opsForZSet().range(key, 0, -1);
    }

    public Boolean zSetAdd(String key, Serializable value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    public <T> Set<T> zSetGet(String key, double score) {
        return (Set<T>) redisTemplate.opsForZSet().rangeByScore(key, score, score);
    }

    // ======================================================

    public RedisConnectionFactory conn() {
        return redisTemplate.getConnectionFactory();
    }
}
