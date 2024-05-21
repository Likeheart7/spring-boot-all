package com.chenx.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class FailureTree implements Serializable {
    private static final long serialVersionUID = 8625485213996002102L;
    @TableId
    private Long ftId;  // 故障树编号
    private String ftCode; // 故障树编码
    private Integer ftType; // 故障树类型
    private String ftDesc; // 故障树描述
}
