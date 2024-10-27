package com.chenx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@Slf4j
public class MessageProvider {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void prepare() {
        // 用于发送方确认回调。需要在配置文件设置publisher-confirm-type: correlated以开启发送方确认
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback(){
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                // 回调是异步在另一个线程执行的
                // correlationData结果是发送消息是设置的值
                log.info("thread [{}] result [{}] data[{}] \n", Thread.currentThread().getName(), ack, correlationData);
                if (ack) {
                    log.info("消息发送成功");
                } else {
                    log.error("消息发送失败");
                }
            }
        });

        /*
        需要在配置文件配置，二者只开一个好像也可以
            template:
              mandatory: true  # 消息投递失败返回客户端
            publisher-returns: true   # 开启消息返回
         */
        // 消息路由失败被返回时回调，可以在这里将失败的路由记录下来
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息路由失败被返回：message = {}, replyCode = {}, replyText = {}, exchange = {}, routingKey = {}",
                        message, replyCode, replyText, exchange, routingKey);
            }
        });
    }

    @GetMapping("/send")
    public String send() {
        rabbitTemplate.convertAndSend("demo.exchange", "demo.route", "six feet under.", new CorrelationData("99"));
        System.out.println(Thread.currentThread().getName());
        return "Send Message";
    }
}
