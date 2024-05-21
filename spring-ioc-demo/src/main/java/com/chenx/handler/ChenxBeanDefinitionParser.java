package com.chenx.handler;

import com.chenx.processor.ChenxBeanPostProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author chenx
 * @description
 * @create 2022-11-28 15:27
 */
public class ChenxBeanDefinitionParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(ChenxBeanPostProcessor.class);
        parserContext.getRegistry().registerBeanDefinition("chenxBeanPostProcessor", rootBeanDefinition);
        return rootBeanDefinition;
    }
}
