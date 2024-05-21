package com.chenx.processor;

import com.chenx.annotation.MyComponent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;

/**
 * @author chenx
 * @description
 * @create 2022-11-25 10:26
 */
public class RegisterByAnnoDemo implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            Class<?> clazz = Class.forName("com.chenx.dao.UserDao");
//            MyComponent annotation = AnnotationUtils.findAnnotation(clazz, MyComponent.class);
//            if (annotation != null){
//                RootBeanDefinition definition = new RootBeanDefinition(clazz);
//                String beanName = clazz.getDeclaredAnnotation(MyComponent.class).value();
//                registry.registerBeanDefinition(beanName, definition);
//            }
            if(clazz.isAnnotationPresent(MyComponent.class)){
                String beanName = clazz.getAnnotation(MyComponent.class).value();
                RootBeanDefinition definition = new RootBeanDefinition(clazz);
                registry.registerBeanDefinition(beanName, definition);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
