package com.chenx.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Event implements Serializable {
    private static final long serialVersionUID = -5798221840385205457L;
    @TableId
    private Long eventId;
    private String eventCode;
    private Double mean;
    private Integer eventType;
    private String eventDesc;
    private Integer eventState;
    private Integer projectId;
}
