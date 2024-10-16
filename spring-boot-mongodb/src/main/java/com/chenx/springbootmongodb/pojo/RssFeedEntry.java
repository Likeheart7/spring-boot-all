package com.chenx.springbootmongodb.pojo;

import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
// 复合索引
@CompoundIndexes({
        @CompoundIndex(name = "idx_feed_publishedDate", def = "{feedName:1, publishedDate:1}"),
        @CompoundIndex(name = "idx_feedName_link", def = "{feedName:1, link: 1}", unique = true)
})
public class RssFeedEntry {
    @Id
    private String id;

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
