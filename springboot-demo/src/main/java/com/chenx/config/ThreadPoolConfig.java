package com.chenx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

@Configuration
public class ThreadPoolConfig {
    @Bean
    public ThreadPoolExecutor calcThreadPool() {
        return new ThreadPoolExecutor(
                5,
                15,
                5,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(1000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    public static void main(String[] args) {

    }
}
