package com.chenx.pojo.vo;

import com.chenx.pojo.Event;
import lombok.Data;

import java.io.Serializable;

@Data
public class FTNodeVO implements Serializable {
    private static final long serialVersionUID = -141996406026908253L;

    private Long fnodeId;
    private Integer transfer;
    private Integer neg;
    private Integer projectId;
    private Event event;
}
