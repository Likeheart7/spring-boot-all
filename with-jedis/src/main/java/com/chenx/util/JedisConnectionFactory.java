package com.chenx.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.xml.transform.Source;

/**
 * @author chenx
 * @description Jedis本身是线程不安全的，一般使用池化技术给每个任务分配一个Jedis实例。
 * @create 2022-12-26 15:36
 */
public class JedisConnectionFactory {
        private static final JedisPool JEDIS_POOL;

        static {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            //设置最大连接总数
            jedisPoolConfig.setMaxTotal(8);
            //设置最大空闲数量
            jedisPoolConfig.setMaxIdle(8);
//            最小空闲数
            jedisPoolConfig.setMinIdle(0);
//            最大等待时间
            jedisPoolConfig.setMaxWaitMillis(1000);

            JEDIS_POOL = new JedisPool(jedisPoolConfig, "localhost", 6379, 1000);
        }

        public static Jedis getJedis(){
            return JEDIS_POOL.getResource();
        }


    public static void main(String[] args) {
        Jedis jedis = JedisConnectionFactory.getJedis();
        String res = jedis.set("chen", "xing");
        System.out.println(res);
        System.out.println(jedis.get("chen"));
    }
}
