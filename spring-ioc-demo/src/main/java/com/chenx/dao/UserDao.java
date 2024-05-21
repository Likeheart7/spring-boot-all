package com.chenx.dao;

import com.chenx.annotation.MyComponent;

import java.util.List;

/**
 * @author chenx
 * @description
 * @create 2022-11-23 15:08
 */
@MyComponent("userDao")
public class UserDao {
    private List<String> str;


    public void setStr(List<String> str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "UserDao{" +
                "str=" + str +
                '}';
    }
}
