package com.chenx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenx.mapper.UserMapper;
import com.chenx.pojo.User;
import com.chenx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

/**
 * @author chenx
 * @description
 * @create 2022-12-16 16:21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper mapper;
    @Override
    public User getByName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>().select("id", "name", "age").eq("name", name);
        this.lambdaUpdate().set(User::getName, "chenxing").update();
        User user = mapper.selectOne(wrapper);
        return user;
    }
}
