package com.chenx.config;

import com.chenx.pojo.Person;
import com.chenx.pojo.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenx
 * @description
 * @create 2022-12-02 10:04
 */

/**
 * proxyBeanMethods: true，代理该类的所有方法，直接调用方法也会先检查容器中是否有实例，每次返回的是同一个对象
 */
@Configuration(proxyBeanMethods = false)
public class MyConfig {
    @Bean
    public Person zhangsan(){
        return new Person("zhangsan", 23);
    }

    @Bean
    public Pet pp(){
        return new Pet("pp", 8);
    }
}
