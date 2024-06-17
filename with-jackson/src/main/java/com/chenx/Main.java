package com.chenx;

import com.chenx.pojo.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
//        测试在一个实体类有非属性getter的，以get开头的方法时，序列化产生的问题
        Product product = new Product(
                "格力空调",
                new BigDecimal("5499.99"),
                3
        );
        System.out.println(product.getTotalPrice());
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(product);
        /*
        结果是： {"productName":"格力空调","productPrice":5499.99,"productAmount":3,"totalPrice":16499.97}
        totalPrice并非属性，但是存在一个以get开头名为getTotalPrice的方法，jackson通过所有get开头的方法来序列化，所以该
        可以通过在该get开头的方法上添加@JsonIgnore注解，来让jackson忽略这个方法
         */
        System.out.println(json);
    }
}