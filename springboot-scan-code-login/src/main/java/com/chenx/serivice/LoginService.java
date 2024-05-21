package com.chenx.serivice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenx.pojo.UserToken;

public interface LoginService extends IService<UserToken> {
    String createQrImage();
}
