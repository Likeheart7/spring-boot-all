package com.chenx;

import com.chenx.config.SpringConfig;
import com.chenx.service.ShowService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author chenx
 * @description
 * @create 2022-11-29 14:57
 */
public class U4T {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    @Test
    public void testAdvice(){
        ShowService showService = context.getBean("showServiceImpl", ShowService.class);
        showService.show1();
    }
}
