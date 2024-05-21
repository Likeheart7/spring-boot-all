package com.chenx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenx.pojo.FailureTree;

public interface FailureTreeService extends IService<FailureTree> {
    void removeByFailureTreeId(Long id);

    void updateByFailureTreeId(FailureTree failureTree);

    FailureTree getByFailureTreeId(Long id);
}
