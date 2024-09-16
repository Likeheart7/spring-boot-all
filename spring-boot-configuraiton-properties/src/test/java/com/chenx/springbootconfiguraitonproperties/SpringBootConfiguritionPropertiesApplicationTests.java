package com.chenx.springbootconfiguraitonproperties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringBootConfigurationPropertiesApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        System.out.println(context.getBean("customerConfigurationProperties"));
    }

}
