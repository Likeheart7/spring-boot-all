package com.chenx.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author chenx
 * @description
 * @create 2022-11-25 10:19
 */
public class MyBeanDefinitionRegisterPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//        System.out.println("MyBeanDefinitionRegisterPostProcessor的postProcessBeanDefinitionRegistry");
//        RootBeanDefinition definition = new RootBeanDefinition("com.chenx.dao.UserDao");
//        registry.registerBeanDefinition("userDao", definition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        System.out.println("MyBeanDefinitionRegisterPostProcessor的postProcessBeanFactory");
    }
}
