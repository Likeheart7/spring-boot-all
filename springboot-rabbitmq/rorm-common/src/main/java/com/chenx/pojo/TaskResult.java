package com.chenx.pojo;

import lombok.Data;

import java.util.List;

@Data
public class TaskResult {
    private Long taskId;
//    1. 计算中 2. 计算失败
    private Integer calcStatus;
}
