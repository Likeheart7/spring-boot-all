package com.chenx.kafka;

import org.apache.kafka.clients.admin.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * 可以通过{@link org.apache.kafka.clients.admin.KafkaAdminClient} 实现对主题、配置、分区的管理
 * 注意：kafka不支持减少分区，因为会引入对被减少分区内消息处理的额外复杂性
 */
public class KafkaAdminClientEx {
    public static final String brokerList = "192.168.194.162:9092";
    public static final String topic = "topic-admin";
    public static void main(String[] args) {
        Properties properties = initConfig();

        AdminClient adminClient = AdminClient.create(properties);

        createTopic(adminClient);   // 创建已经存在的topic会抛出异常，Topic 'topic-admin' already exists.
//        deleteTopic(adminClient);
        listThenDescTopic(adminClient);
        adminClient.close();
    }

    private static void listThenDescTopic(AdminClient adminClient) {
        ListTopicsResult result = adminClient.listTopics();
        try {
            List<String> topicNames = result.listings().get().stream().map(TopicListing::name).collect(Collectors.toList());
            System.out.println(topicNames);
            DescribeTopicsResult describeTopics = adminClient.describeTopics(topicNames);
            Map<String, TopicDescription> stringTopicDescriptionMap = describeTopics.all().get();
            System.out.println(stringTopicDescriptionMap);
        } catch (InterruptedException | ExecutionException exception)  {
            System.err.println(exception.getMessage());
        }
    }

    private static void deleteTopic(AdminClient adminClient) {
        DeleteTopicsResult result = adminClient.deleteTopics(Collections.singletonList(topic));
        try {
            System.out.println(result.all().get());
        } catch (InterruptedException | ExecutionException e)  {
            System.err.println(e.getMessage());
        }
    }

    private static void createTopic(AdminClient adminClient) {
        NewTopic newTopic = new NewTopic(topic, 2, (short) 1);
        CreateTopicsResult result = adminClient.createTopics(Collections.singletonList(newTopic));
        try {
            System.out.println(result.all().get());
        } catch (InterruptedException | ExecutionException exception) {
            System.err.println(exception.getMessage());
        }
    }

    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 100000);
        return  properties;
    }
}
