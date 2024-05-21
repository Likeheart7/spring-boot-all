package com.chenx.controller;

import com.chenx.pojo.CalcFailInfo;
import com.chenx.service.CalcFailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FailInfoController {
    @Autowired
    private CalcFailService calcFailService;
    @GetMapping("/getFailInfo")
    public CalcFailInfo getCalcFailInfo(@RequestParam Long id) {
        return calcFailService.lambdaQuery().eq(CalcFailInfo::getId, id).one();
    }
}
