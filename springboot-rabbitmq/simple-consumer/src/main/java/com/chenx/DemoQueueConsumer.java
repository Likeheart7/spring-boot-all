package com.chenx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class DemoQueueConsumer {

    /**
     * 多个消费者订阅一个队列的情况下，消息只被消费一次，默认是轮询。可以通过preFetch参数配置
     */
    @RabbitListener(queues = "demo.queue1")
    public void consumer2(String msg) {
        log.info("消费者1消费消息: {}", msg);
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "demo.queue1")
    public void consumer1(String msg) {
        log.error("消费者2消费消息: {}", msg);
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
