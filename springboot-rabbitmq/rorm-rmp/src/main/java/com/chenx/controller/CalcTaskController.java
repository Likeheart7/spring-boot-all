package com.chenx.controller;

import com.chenx.pojo.CalcTask;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RestController
@Slf4j
public class CalcTaskController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/calc")
    public String calc(@RequestBody CalcTask calcTask) {
        log.info("接收到计算任务: {}， 准备发给mq", calcTask);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().replace("-", ""));
        rabbitTemplate.convertAndSend("rorm.calc.direct.exchange", "rorm.calc", calcTask, correlationData);
        return calcTask.toString();
    }

    /**
     * 在未配置死信队列的情况下，如果消息过期那么会直接从mq中删除，该消息丢失
     * @param calcTask
     * @return
     */
    @SneakyThrows
    @PostMapping("/calcDelay")
    public String calcDelay(@RequestBody CalcTask calcTask) {
        log.info("接收到包含有效期的消息: {}， 准备发给mq", calcTask);
        rabbitTemplate.convertAndSend("rorm.calc.direct.exchange", "rorm.calc", MessageBuilder
                .withBody(new ObjectMapper().writeValueAsString(calcTask).getBytes(StandardCharsets.UTF_8))
                .setExpiration("10000").build());
        return calcTask.toString();
    }



 }
