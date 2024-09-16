package com.chenx.springbootconfiguraitonproperties.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "customer")
@Configuration
public class CustomerConfigurationProperties {
    private String desc;
    private Integer limit;

    @Override
    public String toString() {
        return "CustomerConfigurationProperties{" +
                "desc='" + desc + '\'' +
                ", limit=" + limit +
                '}';
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
