package com.chenx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
public class ScanLoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScanLoginApplication.class, args);
    }
}
