package com.chenx.springbootasync.service;

import java.util.concurrent.CountDownLatch;

public interface AsyncService {
    void calc();

    void task1(CountDownLatch countDownLatch);

    void task2(CountDownLatch countDownLatch);

    void task3(CountDownLatch countDownLatch);

}
