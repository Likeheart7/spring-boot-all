package com.chenx.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class SimpleConsumer {
    public static final String brokerList = "192.168.194.162:9092"; // kafka地址
        public static final String topic = "topic-demo";
    public static final String groupId = "group.demo"; //分组信息

    public static void main(String[] args) {
        Properties properties = new Properties();
//        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put("bootstrap.servers", brokerList);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);

        // 其他配置
        properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, SimpleConsumerInterceptor.class.getName());   // 消费者拦截器

        // 设置消费者组的名称
        properties.put("group.id", groupId);

        //创建一个消费者客户端实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        // 订阅主题
        consumer.subscribe(Collections.singletonList(topic));
        // 循环消费消息
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(5000));
            System.out.println("本次拉取：" + records.count());
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }
        }

    }
}
