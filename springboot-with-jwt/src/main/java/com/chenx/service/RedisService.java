package com.chenx.service;

/**
 * @author chenx
 * @description
 * @create 2022-12-16 15:46
 */
public interface RedisService {
    void set(String key, String value);

    String get(String key);

    boolean delete(String key);

    Long getExpireTime(String key);
}
