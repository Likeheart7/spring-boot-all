package com.easyexcel.mapper;

import com.easyexcel.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<Post> getAllPost();

    int save(@Param("list") List<Post> cachedList);
}
