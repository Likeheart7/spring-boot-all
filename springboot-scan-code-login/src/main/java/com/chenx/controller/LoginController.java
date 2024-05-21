package com.chenx.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.chenx.serivice.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired private LoginService loginService;
    @GetMapping("/getLoginQr")
    public void login(HttpServletResponse response) {
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 浏览器安全策略，自定义头，在这个字段里的才能访问
        response.addHeader("Access-Control-Expose-Headers", "uuid");
        try {
            String uuid = loginService.createQrImage();
            response.setHeader("uuid", uuid);
            // content代表的就是二维码图片的内容，如果是一个连接会直接跳过去
            QrCodeUtil.generate(" 192.168.2.7:8001/ws/" + uuid,
                                 QrConfig.create().setImg("img/icon.jpg").setWidth(300).setHeight(300),
                            "jpg",
                                 response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
