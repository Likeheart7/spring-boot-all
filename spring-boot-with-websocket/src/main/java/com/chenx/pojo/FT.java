package com.chenx.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("ft")
public class FT implements Serializable {
    private static final long serialVersionUID = -8428793041059549316L;
    @TableId
    private Long ftId;
    private String ftCode;
    private Integer ftType;
    private String ftDesc;
    private Integer tag;
    private Integer projectId;
}
