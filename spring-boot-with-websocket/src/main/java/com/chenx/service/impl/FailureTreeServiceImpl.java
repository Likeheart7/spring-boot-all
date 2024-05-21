package com.chenx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenx.mapper.FailureTreeMapper;
import com.chenx.pojo.FailureTree;
import com.chenx.service.FailureTreeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.chenx.constant.RedisKeyConstant.KEY_FAILURE_TREE;

@Service
public class FailureTreeServiceImpl extends ServiceImpl<FailureTreeMapper, FailureTree> implements FailureTreeService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void removeByFailureTreeId(Long id) {
//        先删除数据库，再删缓存
        this.removeById(id);
        redisTemplate.delete(KEY_FAILURE_TREE + id);
    }

    @Override
    public void updateByFailureTreeId(FailureTree failureTree) {
        this.updateById(failureTree);
        redisTemplate.delete(KEY_FAILURE_TREE + failureTree.getFtId());
    }

    @SneakyThrows
    @Override
    public FailureTree getByFailureTreeId(Long id) {
        String cacheResult = redisTemplate.opsForValue().get(KEY_FAILURE_TREE + id);
        ObjectMapper objectMapper = new ObjectMapper();
        if (Objects.nonNull(cacheResult)) {
            return objectMapper.readValue(cacheResult, FailureTree.class);
        }
        FailureTree result = this.getById(id);
        if (Objects.nonNull(result)) {
            redisTemplate.opsForValue().set(KEY_FAILURE_TREE + result.getFtId(), objectMapper.writeValueAsString(result));
        }
        return result;
    }
}
