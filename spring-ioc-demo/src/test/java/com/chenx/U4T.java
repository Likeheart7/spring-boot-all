package com.chenx;

import com.alibaba.druid.pool.DruidDataSource;
import com.chenx.dao.UserDao;
import com.chenx.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * @author chenx
 * @description
 * @create 2022-11-23 14:58
 */
public class U4T {
    /* BeanFactory 示例 */
    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader read = new XmlBeanDefinitionReader(factory);
        read.loadBeanDefinitions("beans.xml");
//        通过beans.xml中的bean标签的name来获取对象实例, 默认是单例的
        UserService userService = (UserService) factory.getBean("userService");
        UserService userService2 = (UserService) factory.getBean("userService");
        System.out.println(userService == userService2);
        System.out.println(userService);
        UserDao userDao = (UserDao) factory.getBean("userDao");
        System.out.println(userDao);
    }

    /* ApplicationContext 示例 */
    @Test
    public void testApplicationContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Object userService = context.getBean("userService");
        System.out.println(context.getAutowireCapableBeanFactory());
        System.out.println(userService);
    }

    @Test
    public void testBeanTagAttributes(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = (UserService) context.getBean("userService1");
        System.out.println(userService);
        context.close();
    }


    @Test
    public void injectList(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserDao userDao = (UserDao) context.getBean("userDao1");
        System.out.println(userDao);
    }

    @Test
    public void registerDruid(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        DruidDataSource dataSource = context.getBean("druidDataSource", DruidDataSource.class);
        System.out.println(dataSource);
    }

    @Test
    public void testConnection(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Connection connection1 = (Connection) context.getBean("connection");
        Connection connection2 = (Connection) context.getBean("connection");
        System.out.println(connection1);
        System.out.println(connection2);
    }

    @Test
    public void testInstanceMethod(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Date date = (Date) context.getBean("date");
        System.out.println(date);
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss").format(LocalDateTime.now()));
    }

    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println(context.getBean("formattedTime"));
    }


    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    @Test
    public void testBeanFactoryPostProcessor(){
        Object userDao = context.getBean("userDao");
        System.out.println(userDao);
    }
    @Test
    public void testBeanPostProcessor(){
        /*
            (每个bean的实例化都会执行一次）
            demoDao postProcessBeforeInitialization....
            demoDao afterPropertiesSet
            demoDao init...
            demoDao postProcessAfterInitialization
         */
        Object demoDao = context.getBean("userDao");
//        System.out.println(demoDao);
    }

}
