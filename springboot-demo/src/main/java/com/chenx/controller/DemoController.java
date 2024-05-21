package com.chenx.controller;

import com.chenx.pojo.Pet;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenx
 * @description
 * @create 2022-12-02 9:31
 */
@RestController
@Slf4j
public class DemoController {

    @Autowired
    private ThreadPoolExecutor calcThreadPool;
    // 测试demo接口
    @SneakyThrows
    @GetMapping("/demo")
    public String demo(){
        Future<String> task1 = calcThreadPool.submit(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
                log.info(Thread.currentThread().getName() + ": 执行第 " + i + " 次计算");
            }
            return "result1";
        });
        Future<String> task2 = calcThreadPool.submit(() -> {
            for (int i = 0; i < 10; i++) {
                log.info(Thread.currentThread().getName() + ": 执行第 " + i + " 次计算");
            }
            return "calc_result_2";
        });
        return task1.get() + task2.get();
    }

    // 测试日志traceId
    @GetMapping("/testLog")
    public String testLog(){
        log.info("info");
        log.warn("warn");
        log.error("error");
//        如果不调用，其他没有用put traceId的接口可能会用同一个线程，而这个线程里面有上次逻辑put进去的traceId，导致日志traceId混乱
//        ThreadContext.clearMap();
        return "success";
    }
    // 测试pet接口
    @PostMapping("/testTrace")
    public Pet testTrace(@RequestBody Pet pet){
        log.info("{}", pet);
        return pet;
    }
}
