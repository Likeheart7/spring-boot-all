package com.chenx;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMessage2Queue() {
        String queueName = "demo.queue1";
        String message = "消息来了";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message);
        }
    }
    @Test
    public void sendMessage2Fanout() {
        String queueName = "demo.queue1";
        String message = "消息来了";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message);
        }
    }
}
