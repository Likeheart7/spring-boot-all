package com.chenx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenx.pojo.User;

/**
 * @author chenx
 * @description
 * @create 2022-12-16 16:21
 */
public interface UserService extends IService<User>{
    User getByName(String name);
}
