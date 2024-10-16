package com.chenx.springbootmongodb.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class NameValue {
    @Id
    private String name;
    private Integer value;
}
