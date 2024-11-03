package com.chenx.kafka;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class SimpleProducerInterceptor implements ProducerInterceptor<String, String> {
    private volatile long sendSuccess = 0;
    private volatile long sendFailure = 0;

    /**
     * 在消息序列化和计算分区前调用
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String modifyValue = "Interceptor-prefix " + record.value();
        return new ProducerRecord<>(record.topic(), record.partition(), record.timestamp(), record.key(), modifyValue, record.headers());
    }

    /**
     * 消息被应答前或消息发送失败时调用
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            sendFailure ++;
        } else {
            sendSuccess ++;
        }
    }

    /**
     * 关闭拦截器时调用
     */
    @Override
    public void close() {
        double successRadio = (double) sendSuccess / (sendSuccess + sendFailure);
        System.out.println("消息发送成功条数：" + sendSuccess + "\n消息发送失败条数：" + sendFailure + "\n消息发送成功率：" + successRadio * 100 + "%");
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
