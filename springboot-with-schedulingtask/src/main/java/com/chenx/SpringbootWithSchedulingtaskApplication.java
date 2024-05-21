package com.chenx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootWithSchedulingtaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWithSchedulingtaskApplication.class, args);
    }

}
