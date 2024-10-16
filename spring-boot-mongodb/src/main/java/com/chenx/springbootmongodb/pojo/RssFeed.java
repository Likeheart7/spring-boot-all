package com.chenx.springbootmongodb.pojo;

import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class RssFeed {
    public static final String COL_ENTRY_COUNT = "10";
    public static final String COL_NAME = "col";
    @Id
    private String id;
    // Feed名称
    @Indexed(unique = true)
    private String name;
    // Feed Url
    private String url;

    // RSS 版本类型
    private String type;

    // RSS标题
    private String title;

    // RSS描述
    private String description;

    // RSS发布日期
    private DateTime publishDate;

    // RSS 条目数量
    private Integer entryCount = 0;
}
