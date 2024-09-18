package com.chenx.springbootasync.controller;

import com.chenx.springbootasync.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {
    @Autowired
    private AsyncService asyncService;

    @GetMapping("/async")
    public String async() {
        asyncService.calc();
        return "success";
    }
}
