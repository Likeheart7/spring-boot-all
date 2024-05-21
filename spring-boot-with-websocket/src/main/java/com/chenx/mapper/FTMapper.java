package com.chenx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenx.pojo.FT;
import com.chenx.pojo.FTNode;
import com.chenx.pojo.vo.FTNodeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FTMapper extends BaseMapper<FT> {
    void removeFtByFtId(@Param("ftId") Long ftId);
    void removeNodeByFtId(@Param("ftId") Long ftId);

    void updateNode(@Param("ftNode") FTNode ftNode);

    void addFTNode();

    List<FTNodeVO> getNodeWithEventByFtId(@Param("ftId") Long ftId);

}
