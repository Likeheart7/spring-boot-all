package com.chenx.factories;

import com.chenx.service.UserService;
import com.chenx.service.impl.UserServiceImpl;

/**
 * @author chenx
 * @description
 * @create 2022-11-24 10:33
 */
public class UserServiceFactory {
    public static UserService getUserService(String arg){
        System.out.println(arg);
        return new UserServiceImpl();
    }
}
