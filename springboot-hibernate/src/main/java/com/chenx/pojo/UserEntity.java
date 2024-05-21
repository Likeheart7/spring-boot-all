package com.chenx.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author chenx
 * @create 2023-07-17 14:54
 */
//这个data注解和hibernate无关，是lombok中的，等价于set和get方法
@Data
//告诉springboot这是一个实体类，和数据库中表对应
@Entity
//该实体类对应的数据库的那个表，name的值是表名字，这里可能会爆红，使用idea自带的数据库连接这个数据库就好了，注意这个表名字必须和数据中的表名字一摸一样
@Table(name="url_info")
public class UserEntity{
    @Id
    //这是设置主键自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    // @Column注解可以设置表中字段名和实体类的成员变量名不一样的问题，保证映射正确
    @Column(name="url_name")
    private String urlName;

}

