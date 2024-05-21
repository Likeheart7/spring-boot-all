package com.chenx.serivice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenx.mapper.LoginMapper;
import com.chenx.pojo.UserToken;
import com.chenx.serivice.LoginService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, UserToken> implements LoginService {
    @Override
    public String createQrImage() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        UserToken userToken = new UserToken();
        userToken.setUuid(uuid);
        userToken.setCreateTime(new Date());
        this.save(userToken);
        return uuid;
    }
}
