package com.chenx.controller;

import com.chenx.common.ResponseData;
import com.chenx.pojo.Component;
import com.chenx.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comp")
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    @GetMapping("/getCompById")
    public ResponseData getCompById(@RequestParam("id") Long id) {
        return ResponseData.ok().data(componentService.getById(id));
    }
    @GetMapping("/getALlComp")
    public ResponseData getALlComp() {
        return ResponseData.ok().data(componentService.list());
    }

    @PostMapping("/updateComp")
    public ResponseData updateComp(@RequestBody Component component) {
        componentService.updateById(component);
        return ResponseData.ok();
    }
    @DeleteMapping("/delComp")
    public ResponseData delComp(@RequestParam Long id) {
        componentService.removeById(id);
        return ResponseData.ok();
    }
}
