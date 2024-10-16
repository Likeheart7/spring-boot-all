package com.chenx.springbootmongodb.pojo.vo;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@Builder
public class FeedInfo {
    private String name;

    private String type;

    private String title;

    private String description;

    private DateTime publishDate;

    private Integer entryCount = 0;
}
