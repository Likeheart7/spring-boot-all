package com.chenx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenx.pojo.FT;
import com.chenx.pojo.FTNode;
import com.chenx.pojo.vo.FTVO;

public interface FTService extends IService<FT> {
    void removeByFtId(Long ftId);

    void updateFtByFtId(FT ft);

    void updateNode(FTNode ftNode);

    void addFTNode(FTNode ftNode);

    FTVO getFt(Long ftId);
}
