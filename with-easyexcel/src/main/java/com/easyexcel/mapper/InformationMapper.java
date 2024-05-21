package com.easyexcel.mapper;

import com.easyexcel.pojo.Information;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InformationMapper {
    int save(List<Information> list);
}
