package com.chenx.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author chenx
 * @description
 * @create 2022-11-28 15:28
 */
public class ChenxBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("chenxBeanPostProcess...");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
}
