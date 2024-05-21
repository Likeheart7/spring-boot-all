package com.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.easyexcel.mapper.InformationMapper;
import com.easyexcel.pojo.Information;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
public class InformationListener implements ReadListener<Information> {
    public static final Integer BatchCount = 5;
    public final List<Information> cachedList = ListUtils.newArrayListWithExpectedSize(BatchCount);
    private final InformationMapper informationMapper;


    public InformationListener(InformationMapper informationMapper) {
        this.informationMapper = informationMapper;
    }

    @Override
    public void invoke(Information data, AnalysisContext context) {
        cachedList.add(data);
        if(cachedList.size() >= BatchCount) {
            saveData();
            cachedList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("信息数据导入完成");
    }

    private void saveData(){
        informationMapper.save(cachedList);
    }
}
