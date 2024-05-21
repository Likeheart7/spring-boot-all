package com.chenx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@EnableAspectJAutoProxy(exposeProxy = true)
public class AsyncConfig {
    /**
     * @return 计算用的线程池
     */
    @Bean
    public Executor calcThreadPool(){
        ThreadPoolTaskExecutor calcThreadPool = new ThreadPoolTaskExecutor();
        calcThreadPool.setCorePoolSize(10); //核心线程数
        calcThreadPool.setMaxPoolSize(20);  //最大线程数
        calcThreadPool.setQueueCapacity(1000); //队列大小
        calcThreadPool.setKeepAliveSeconds(300); //线程最大空闲时间
        calcThreadPool.setThreadNamePrefix("calc-thead-pool-"); // 指定用于新创建的线程名称的前缀。
        calcThreadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略
        calcThreadPool.initialize();
        return calcThreadPool;
    }
}
