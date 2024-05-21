package com.chenx.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class FTVO {
    private Long ftId;
    private String ftCode;
    private Integer ftType;
    private String ftDesc;
    private Integer tar;
    private Integer projectId;
    private List<FTNodeVO> ftNodes;
}
