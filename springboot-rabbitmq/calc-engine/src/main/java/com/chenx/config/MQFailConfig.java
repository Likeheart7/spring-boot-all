package com.chenx.config;

import com.chenx.pojo.CalcTask;
import com.chenx.service.CalcFailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "spring.rabbitmq.listener.simple.retry", name = "enabled", havingValue = "true")
public class MQFailConfig {
    @Autowired
    private CalcFailService calcFailService;
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("rorm.calc.fail.queue"),
            key = {"calc.fail"},
            exchange = @Exchange("rorm.calc.fail.exchange")
    ))
    public void calcFailOperation(Message message){
        String traceId = UUID.randomUUID().toString().replace("-", "");
        ThreadContext.put("traceId", traceId);
        log.warn("【监测到失败的计算任务，打印异常堆栈】");
        log.warn("{}", message.getMessageProperties().getHeaders().get("x-exception-stacktrace"));
        calcFailService.saveFailMessageInfo(message, traceId);
        ThreadContext.clearMap();
    }

    /**
     * 注册一个策略，让消息在重试n次之后仍然失败的情况下，发给其他指定交换机
     */
    @Bean
    public MessageRecoverer messageRecoverer(RabbitTemplate rabbitTemplate){
        return new RepublishMessageRecoverer(rabbitTemplate,"rorm.calc.fail.exchange", "calc.fail");
    }
}
