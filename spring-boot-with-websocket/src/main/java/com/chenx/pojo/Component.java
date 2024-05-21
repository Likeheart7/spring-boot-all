package com.chenx.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Component implements Serializable {
    private static final long serialVersionUID = -5346436300181546936L;
    @TableId
    private Long compId; // 编号
    private String compCode; // 编码
    private String compName; //名称
    private String compDesc; // 描述
    private Long nppId; // 电站编号
}
