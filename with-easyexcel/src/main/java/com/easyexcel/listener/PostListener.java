package com.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.easyexcel.mapper.PostMapper;
import com.easyexcel.pojo.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PostListener implements ReadListener<Post> {

    public static final int BATCH_COUNT = 5;
    private static final ObjectMapper om = new ObjectMapper();
    private final List<Post> cachedList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    private final PostMapper postMapper;


    public PostListener(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

//    解析每一行都会调用
    @SneakyThrows
    @Override
    public void invoke(Post data, AnalysisContext context) {
        log.info("解析一条数据{}", om.writeValueAsString(data));
        cachedList.add(data);
        if (cachedList.size() >= BATCH_COUNT) {
            saveData();
            cachedList.clear();
        }
    }

    private void saveData() {
        log.info("{}条数据，开始插入数据库", cachedList.size());
        postMapper.save(cachedList);
        log.info("{}条数据，插入成功", cachedList.size());
    }

    //    解析所有数据后会调用
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("所有数据解析完成");
    }
}
