package com.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.listener.PageReadListener;
import com.easyexcel.listener.PostListener;
import com.easyexcel.mapper.PostMapper;
import com.easyexcel.pojo.Post;
import com.easyexcel.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private PostMapper postMapper;

    @GetMapping("/getAllPost")
    public List<Post> getDemo(){
        return postService.getAllPost();
    }

    @SneakyThrows
    @PostMapping("/uploadExcel")
    public String uploadExcel(MultipartFile file){
        EasyExcel.read(file.getInputStream(), Post.class, new PostListener(postMapper)).sheet().doRead();
        return "over";
    }

    @GetMapping("writeExcel")
    public void writeExcel(){
        postService.writeExcel();
    }

    @PostMapping("readMultiSheet")
    public void readMultiSheet(MultipartFile file) {
        postService.readMultiSheet(file);
    }
}
