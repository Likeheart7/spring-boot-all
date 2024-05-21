package com.chenx.controller;

import com.chenx.common.ResponseData;
import com.chenx.pojo.FT;
import com.chenx.pojo.FTNode;
import com.chenx.pojo.vo.FTVO;
import com.chenx.service.FTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ft")
public class FTController {

    @Autowired
    private FTService ftService;

    @GetMapping("/getFT/{ftId}")
    public ResponseData getFT(@PathVariable Long ftId) {
        FTVO ft = ftService.getFt(ftId);
        return ResponseData.ok().data(ft);
    }

    @PostMapping("/updateFT")
    public ResponseData updateFT(@RequestBody FT ft) {
        ftService.updateFtByFtId(ft);
        return ResponseData.ok();
    }

    @PostMapping("/updateFTNode")
    public ResponseData updateFTNode(@RequestBody FTNode ftNode) {
        ftService.updateNode(ftNode);
        return ResponseData.ok();
    }

    @PostMapping("/delFT/{ftId}")
    public ResponseData delFT(@PathVariable Long ftId) {
        ftService.removeByFtId(ftId);
        return ResponseData.ok();
    }

    @PostMapping("/addFTNode")
    public ResponseData addFTNode(@RequestBody FTNode ftNode) {
        ftService.addFTNode(ftNode);
        return ResponseData.ok();
    }
}
