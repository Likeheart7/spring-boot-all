package com.chenx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

// 通过这个注解也可以忽略序列化产生的totalPrice
@JsonIgnoreProperties("totalPrice")
public class Product {
    private String productName;
    private BigDecimal productPrice;
    private Integer productAmount;

    public Product(String productName, BigDecimal productPrice, Integer productAmount) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
    }

    // 用于测试jackson基于getter反序列化的。以get开头的方法
//    @JsonIgnore // 在方法上添加该注解可以防止该方法被当作getter序列化成totalPrice
    public BigDecimal getTotalPrice() {
        return productPrice.multiply(new BigDecimal(productAmount));
    }

    // 普通 getter
    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public Integer getProductAmount() {
        return productAmount;
    }


}
