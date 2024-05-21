package com.chenx.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chenx
 * @description
 * @create 2022-12-02 14:35
 */
@ConfigurationProperties("spring.demo")
@Data
public class DemoProperties {
    private String name;
    private Integer age;
}
