package com.chenx.service.impl;

import com.chenx.service.ShowService;
import org.springframework.stereotype.Service;

/**
 * @author chenx
 * @description
 * @create 2022-11-29 15:15
 */
@Service
public class ShowServiceImpl implements ShowService {
    @Override
    public void show1() {
        System.out.println("show1....");
    }

    @Override
    public void show2() {
        System.out.println("show2....");
    }
}
