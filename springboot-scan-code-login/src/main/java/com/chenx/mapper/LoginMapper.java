package com.chenx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenx.pojo.UserToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<UserToken> {
}
