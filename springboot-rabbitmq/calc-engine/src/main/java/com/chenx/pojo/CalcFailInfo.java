package com.chenx.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName(autoResultMap = true)
public class CalcFailInfo implements Serializable {
    private String id;
    private Long taskId;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Integer> statusChange;
    private String exceptionInfo;
    private String traceId;
    private String fromExchange;
    private String fromBindingKey;
}
