package com.chenx.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author chenx
 * @description
 * @create 2022-11-25 9:52
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor的postProcessBeanFactory");

        /*将UserService映射到UserDao类上*/
//        beanFactory.getBeanDefinition("userService").setBeanClassName("com.chenx.dao.UserDao");

        /*通过BeanFactoryPostProcessor实现动态注册bean*/
//        建议使用BeanFactoryPostProcessor的子接口BeanDefinitionRegisterPostProcessor
//        RootBeanDefinition definition = new RootBeanDefinition();
//        definition.setBeanClassName("com.chenx.dao.UserDao");
//        DefaultListableBeanFactory factory = (DefaultListableBeanFactory) beanFactory;
//        factory.registerBeanDefinition("userDao", definition);
    }
}
