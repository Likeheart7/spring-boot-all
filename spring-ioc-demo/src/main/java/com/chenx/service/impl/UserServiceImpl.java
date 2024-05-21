package com.chenx.service.impl;

import com.chenx.dao.UserDao;
import com.chenx.service.UserService;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author chenx
 * @description
 * @create 2022-11-23 14:40
 */
public class UserServiceImpl implements UserService, InitializingBean {
    private UserDao userDao;

    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
        System.out.println("注入userDao完毕");
    }

    public void init(){
        System.out.println("init...");
    }

    public void destroy(){
        System.out.println("destroy...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("实现InitializingBean，重写的afterPropertiesSet()，在init-method前执行");
    }
}
