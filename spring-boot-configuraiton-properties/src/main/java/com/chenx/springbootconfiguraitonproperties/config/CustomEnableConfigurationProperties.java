package com.chenx.springbootconfiguraitonproperties.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CustomerConfigurationProperties.class)
public class CustomEnableConfigurationProperties {
    
}
