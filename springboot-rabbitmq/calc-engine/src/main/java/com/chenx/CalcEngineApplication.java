package com.chenx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class CalcEngineApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalcEngineApplication.class, args);
    }
}
