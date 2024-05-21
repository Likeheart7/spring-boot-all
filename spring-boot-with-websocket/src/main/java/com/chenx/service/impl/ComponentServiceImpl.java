package com.chenx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenx.mapper.ComponentMapper;
import com.chenx.pojo.Component;
import com.chenx.service.ComponentService;
import org.springframework.stereotype.Service;

@Service
public class ComponentServiceImpl extends ServiceImpl<ComponentMapper, Component> implements ComponentService {
}
