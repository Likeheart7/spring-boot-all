package com.chenx.controller;

import com.chenx.pojo.User;
import com.chenx.util.RedisStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @Autowired
    private RedisStringUtil redisStringUtil;

    @GetMapping("/test")
    public void test() {
        User user = redisStringUtil.get("29597", User.class);
        System.out.println(user);
    }
}
