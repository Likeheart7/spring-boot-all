package com.chenx.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class SimpleProvider {
    public static final String brokerList = "192.168.5.136:9092";
    public static final String topic = "topic-demo";

    public static void main(String[] args) {
        Properties properties = initConfig();


        // 配置生产者客户端参数，创建producer实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        // 构建消息
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello kafka");
        // 发送消息
        try {
//            for (int i = 0; i < 10; i++) {
                producer.send(record, (metadata, exception)-> {
                    if (exception != null) {
                        System.out.println(exception.getMessage());
                    }
                    System.out.println(metadata);
                });
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

    private static Properties initConfig() {
        Properties properties = new Properties();
//        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("bootstrap.servers", brokerList);
        // 创建KafkaProducer必须的三个配置
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());   // 消息键序列化方式
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // 消息值序列化方式
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);    // 服务地址

        // 其他配置项
//        properties.put(ProducerConfig.RETRIES_CONFIG, 10);  // 发送失败重试次数，只在可重试的异常情况下会重试
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, SimpleProducerInterceptor.class.getName()); // 发送者拦截器
        return properties;
    }
}
