package com.chenx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenx.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author chenx
 * @description
 * @create 2022-12-16 16:14
 */
public interface UserMapper extends BaseMapper<User> {
}
