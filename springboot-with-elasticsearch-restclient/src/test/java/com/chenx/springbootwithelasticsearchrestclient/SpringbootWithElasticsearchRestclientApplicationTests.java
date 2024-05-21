package com.chenx.springbootwithelasticsearchrestclient;

import com.chenx.springbootwithelasticsearchrestclient.config.ElasticSearchConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class SpringbootWithElasticsearchRestclientApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    @Test
    void contextLoads() {
        System.out.println(client);
    }

    /*
    简单插入数据
     */
    @Test
    public void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        User user = new User();
        user.setAge(10);
        user.setName("chenxing");
        ObjectMapper om = new ObjectMapper();
        String str = om.writeValueAsString(user);
        indexRequest.source(str, XContentType.JSON);
        IndexResponse index = client.index(indexRequest, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(index);
    }

    /*
    复杂索引数据
     */
    @SneakyThrows
    @Test
    public void searchData() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("customer");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("address", "mill"));
        builder.aggregation(AggregationBuilders.terms("genderAgg").field("gender"));
        builder.aggregation(AggregationBuilders.avg("balanceAvg").field("balance"));
        searchRequest.source(builder);
        System.out.println(builder);
        SearchResponse response = client.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(response);
    }


}
@Data
class User {
    private String name;
    private int age;
}
