package com.chenx.controller;

import com.chenx.common.ResponseData;
import com.chenx.pojo.FailureTree;
import com.chenx.service.FailureTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/failureTree")
public class FailureTreeController {
    @Autowired
    private FailureTreeService failureTreeService;


    @GetMapping("/getFailureTreeById")
    public ResponseData getFailureTreeById(@RequestParam("id") Long id) {
        return ResponseData.ok().data(failureTreeService.getByFailureTreeId(id));
    }
    @GetMapping("/getAllFailureTree")
    public ResponseData getAllFailureTree() {
        return ResponseData.ok().data(failureTreeService.list());
    }
    @PostMapping("/updateFailureTree")
    public ResponseData updateFailureTree(@RequestBody FailureTree failureTree) {
        failureTreeService.updateByFailureTreeId(failureTree);
        return ResponseData.ok();
    }

    @DeleteMapping("/delFailureTree")
    public ResponseData delFailureTree(@RequestParam Long id) {
        failureTreeService.removeByFailureTreeId(id);
        return ResponseData.ok();
    }
}
