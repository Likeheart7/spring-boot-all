package com.chenx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CycleConfiguration {
    @Autowired public OtherCycleConfiguration otherCycleConfiguration;
}
