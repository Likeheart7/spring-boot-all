package com.easyexcel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.easyexcel.listener.InformationListener;
import com.easyexcel.listener.PostListener;
import com.easyexcel.mapper.InformationMapper;
import com.easyexcel.mapper.PostMapper;
import com.easyexcel.pojo.Information;
import com.easyexcel.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostMapper postMapper;
    @Autowired
    InformationMapper informationMapper;

    public List<Post> getAllPost(){
        return postMapper.getAllPost();
    }

    public void writeExcel() {
        final List<Post> allPost = postMapper.getAllPost();
        try (ExcelWriter excelWriter = EasyExcel.write("./demo.xlsx", Post.class).build()) {
            for (Post post : allPost) {
                WriteSheet writeSheet = EasyExcel.writerSheet(post.getPostName()).build();
                excelWriter.write(allPost,writeSheet);
            }
        }

    }

    /**
     *  读取多个sheet
     * @param file 上传的文件
     */
    public void readMultiSheet(MultipartFile file) {
        try (ExcelReader excelReader = EasyExcel.read(file.getInputStream()).build()) {
            ReadSheet readSheet1 =
                    EasyExcel.readSheet(0).head(Information.class).registerReadListener(new InformationListener(informationMapper)).build();
            ReadSheet readSheet2 =
                    EasyExcel.readSheet(1).head(Post.class).registerReadListener(new PostListener(postMapper)).build();
            excelReader.read(readSheet1, readSheet2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
