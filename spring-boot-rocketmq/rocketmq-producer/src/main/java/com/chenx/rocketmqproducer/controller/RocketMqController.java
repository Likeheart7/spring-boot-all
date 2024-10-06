package com.chenx.rocketmqproducer.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RocketMqController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    public static final String topic = "demoTopic";

    @GetMapping("/send")
    public String send() {
        String destination = topic;

        SendResult sendResult = rocketMQTemplate.syncSend(
                destination,
                MessageBuilder.withPayload("hello，这是消息")
                        .setHeader(MessageConst.PROPERTY_KEYS, "message-id-0001")
                        .build()
        );
        if (sendResult != null) {
            if (sendResult.getSendStatus() == SendStatus.SEND_OK){
                log.info("success");
            }
        }
        return "send success";
    }
}
