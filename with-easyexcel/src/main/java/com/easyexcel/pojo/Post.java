package com.easyexcel.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Post {
    @ExcelIgnore
    private Integer id;
    @ExcelProperty("职位名称")
    private String postName;
    @ExcelProperty("工资待遇")
    private String salary;
    @ExcelProperty("行政级别")
    private String level;
}
