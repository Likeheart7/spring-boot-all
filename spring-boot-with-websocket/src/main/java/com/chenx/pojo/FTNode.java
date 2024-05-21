package com.chenx.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class FTNode implements Serializable {
    private static final long serialVersionUID = -9002018776647714844L;
    private Long ftId;
    @TableId
    private Long fnodeId;
    private Long eventId;
    private Integer transfer;
    private Integer neg;
    private Integer projectId;
}
