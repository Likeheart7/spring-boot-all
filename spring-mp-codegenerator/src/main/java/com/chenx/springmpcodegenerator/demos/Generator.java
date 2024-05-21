package com.chenx.springmpcodegenerator.demos;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;


public class Generator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/likepan?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "root";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("chenxing") // 设置作者
                            .outputDir("C:\\Users\\chenxing\\Desktop\\docs\\genout"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.chenx") // 设置父包名
                            .moduleName("likepan") // 设置父包模块名
                            .entity("pojo")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\mywork\\yaspeed\\PB-RMP\\code\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("file_info") // 设置需要生成的表名
                            .entityBuilder()
                            .enableLombok()
                            .serviceBuilder();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
