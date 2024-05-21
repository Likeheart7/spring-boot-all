package com.chenx;

import com.chenx.config.SpringConfig;
import com.chenx.dao.UserDao;
import javafx.application.Application;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * @author chenx
 * @description
 * @create 2022-11-28 15:55
 */
public class U4T {
//    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    @Test
    public void testAnnotationComponent(){
        Object userDao1 = context.getBean("userDao");
        Object userDao2 = context.getBean("userDao");
        Object userDao3 = context.getBean("userDao");
        System.out.println(userDao1);
        System.out.println(userDao2);
        System.out.println(userDao3);
    }

    @Test
    public void test01(){
        UserDao userDao = new UserDao("123");
        System.out.println(userDao);
        System.out.println(userDao.getClass().isAnnotationPresent(Component.class));
    }
}
