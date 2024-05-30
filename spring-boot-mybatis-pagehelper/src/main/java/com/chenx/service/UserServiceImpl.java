package com.chenx.service;

import com.chenx.mapper.UserMapper;
import com.chenx.pojo.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    public List<User> getUser(Integer pageNum, Integer pageSize) {
        // 下面三种方式会将sql语句修改为  select id, username as name, password, sex from user LIMIT ?, ?
        // 加了limit
        Page<User> users = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> {
            userMapper.getAllUser();
        });
        // 这种方式能拿到更多消息，包括总页数、上一页页数、是否是首页/尾页等
        PageInfo<User> userInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            userMapper.getAllUser();
        });

        PageHelper.startPage(pageNum, pageSize);    // 只会在接下来第一个查询里生效
        return userMapper.getAllUser();
    }
}
