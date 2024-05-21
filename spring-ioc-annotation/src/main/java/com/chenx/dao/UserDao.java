package com.chenx.dao;

import com.chenx.annotation.DemoAnno;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author chenx
 * @description
 * @create 2022-11-28 15:52
 */
@DemoAnno
public class UserDao {
    public UserDao(){
        System.out.println("无参");
    }
    public UserDao(String Message){
        System.out.println("有参");
    }
}
