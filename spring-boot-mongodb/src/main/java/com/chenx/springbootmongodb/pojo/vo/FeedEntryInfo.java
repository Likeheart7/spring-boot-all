package com.chenx.springbootmongodb.pojo.vo;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
@Builder
public class FeedEntryInfo {
    // 名称
    private String feedName;
    // 标题
    private String title;
    // 描述
    private String description;
    // 链接
    private String link;
    // 类别
    private List<String> categories;
    // 作者
    private String author;
    // 发布日期
    @Indexed
    private DateTime publishedDate;
}
