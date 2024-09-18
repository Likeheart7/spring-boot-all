package com.chenx.springbootasync.service.impl;

import com.chenx.springbootasync.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class AsyncServiceImpl implements AsyncService {
    //    @Autowired
    /*
       注入自己时，因为三级缓存不能处理@Async的代理bean的循环依赖，所以一般需要配合@Lazy处理
       对于Spring AOP的代理，因为AbstractAutoProxyCreator重写了getEarlyReference方法，所以三级缓存实际上处理了Spring AOP的代理bean循环依赖的问题。
     */
//    @Lazy
//    private AsyncService asyncService;
    @Autowired
    private ApplicationContext context;

    @Override
    public void calc() {
        AsyncService asyncService = context.getBean("asyncServiceImpl", AsyncService.class);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        asyncService.task1(countDownLatch);
        asyncService.task2(countDownLatch);
        asyncService.task3(countDownLatch);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            // pass
        }
        System.out.println("计算完成");
    }

    @Async
    @Override
    public void task1(CountDownLatch countDownLatch) {
        try {
            TimeUnit.SECONDS.sleep(8);
            System.out.println("task1 计算完成...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }

    @Async
    @Override
    public void task2(CountDownLatch countDownLatch) {
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("task2 计算完成...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }

    @Async
    @Override
    public void task3(CountDownLatch countDownLatch) {
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("task3 计算完成...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }
}

