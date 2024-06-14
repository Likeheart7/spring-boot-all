package com.chenx;

import com.chenx.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Slf4j
public class SpringbootWithRedisApplication {

    public static void main(String[] args) throws InterruptedException, JsonProcessingException {

        ApplicationContext cxt = SpringApplication.run(SpringbootWithRedisApplication.class, args);
        log.info("--- {} ---", cxt.getBeanDefinitionCount());
        StringRedisTemplate stringRedisTemplate = cxt.getBean(StringRedisTemplate.class);
        User user = new User(29597, "chenxing", 25, "sleep");
        ObjectMapper om = new ObjectMapper();
        stringRedisTemplate.opsForValue().set(user.getId() + "", om.writeValueAsString(user));
    }

}
