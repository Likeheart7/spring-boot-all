package com.chenx.dao;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author chenx
 * @description
 * @create 2022-11-25 14:35
 */
public class DemoDao implements InitializingBean {
    public void init(){
        System.out.println("demoDao init...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("demoDao afterPropertiesSet");
    }
}
