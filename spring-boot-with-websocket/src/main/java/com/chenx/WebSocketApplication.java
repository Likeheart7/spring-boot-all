package com.chenx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@MapperScan("com.chenx.mapper")
public class WebSocketApplication  {
    private ApplicationContext context;
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("started...");
//        System.out.println(context.getBean("redisTemplate", RedisTemplate.class));
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.context = applicationContext;
//    }
}
