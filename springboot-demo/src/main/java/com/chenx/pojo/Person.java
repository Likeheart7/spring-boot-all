package com.chenx.pojo;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author chenx
 * @description
 * @create 2022-12-02 10:04
 */
@Data
@AllArgsConstructor
public class Person {
    private String name;
    private Integer age;
}
