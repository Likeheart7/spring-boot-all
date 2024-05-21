package com.chenx.springcloudwithnacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringCloudWithNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudWithNacosApplication.class, args);
    }

    @Bean
    @LoadBalanced   //让resttemplate知道怎么选择远程服务的实例(轮询)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
