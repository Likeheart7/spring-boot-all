package com.chenx.config;

import com.chenx.properties.DemoProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author chenx
 * @description
 * @create 2022-12-02 14:40
 */
@Configuration
/*
   prefix: 前缀
   name/value: 具体的属性名
   havingValue：与value/name属性名的值比较，必须结合value/name使用
 */
@ConditionalOnProperty(
        prefix = "demo.enable",
        value = "config",
        havingValue = "enabled"
)
@EnableConfigurationProperties(DemoProperties.class)
public class DemoPropertiesAutoConfig {
}
