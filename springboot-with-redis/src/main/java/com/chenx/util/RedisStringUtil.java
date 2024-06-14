package com.chenx.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisStringUtil {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> type) {
        String obj = redisTemplate.opsForValue().get(key);
        ObjectMapper om = new ObjectMapper();
        try {
            return om.readValue(obj, type);
        } catch (JsonProcessingException e) {
            System.err.println("反序列化异常");
        }
        return null;
    }
}
