package com.chenx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
@Slf4j
public class SpringbootWithRedisApplication {

    public static void main(String[] args) throws InterruptedException {

       ApplicationContext cxt = SpringApplication.run(SpringbootWithRedisApplication.class, args);
       log.info("--- {} ---",cxt.getBeanDefinitionCount());
        StringRedisTemplate stringRedisTemplate = cxt.getBean(StringRedisTemplate.class);
        log.info(stringRedisTemplate.opsForValue().get("chen"));
        stringRedisTemplate.opsForValue().set("li", "小羊");
    }

}
