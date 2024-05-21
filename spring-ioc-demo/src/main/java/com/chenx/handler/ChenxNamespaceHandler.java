package com.chenx.handler;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author chenx
 * @description
 * @create 2022-11-28 15:25
 */
public class ChenxNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        this.registerBeanDefinitionParser("demo-target", new ChenxBeanDefinitionParser());
    }
}
