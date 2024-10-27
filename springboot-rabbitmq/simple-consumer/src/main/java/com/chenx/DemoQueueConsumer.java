package com.chenx;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class DemoQueueConsumer {

    /**
     * 多个消费者订阅一个队列的情况下，消息只被消费一次，默认是轮询。可以通过preFetch参数配置
     */
    @SneakyThrows
    @RabbitListener(queues = "demo.queue")
    public void consumer2(Message msg, Channel channel) {
        log.info("消费者1消费消息: {}", msg);
        long index = msg.getMessageProperties().getDeliveryTag();
        log.info("index: {}", index);
        channel.basicAck(index, false);
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注意开启了手动确认，而消费者10s后才确认，其间消息一直存在queue中，并处于unacked状态
     * @param msg
     * @param channel
     */
    @SneakyThrows
    @RabbitListener(queues = "demo.queue")
    public void consumer1(Message msg, Channel channel) {
        log.error("消费者2消费消息: {}", new String(msg.getBody()));
        try {TimeUnit.SECONDS.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
        long tag = msg.getMessageProperties().getDeliveryTag();
        channel.basicAck(tag, false);
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
