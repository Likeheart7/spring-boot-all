package com.chenx.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CalcTask implements Serializable {
    private Long taskId;
    private List<Integer> statusChange;
}
