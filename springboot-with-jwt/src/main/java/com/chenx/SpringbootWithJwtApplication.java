package com.chenx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chenx.mapper")
public class SpringbootWithJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWithJwtApplication.class, args);
    }

}
