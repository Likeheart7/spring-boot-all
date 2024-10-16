package com.chenx.springbootmongodb.controller;

import com.chenx.springbootmongodb.pojo.RssFeed;
import com.chenx.springbootmongodb.pojo.RssFeedEntry;
import com.chenx.springbootmongodb.pojo.vo.FeedEntryInfo;
import com.chenx.springbootmongodb.pojo.vo.FeedInfo;
import com.chenx.springbootmongodb.service.RssFeedManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FeedController {
    public static final int PAGE_SIZE = 10;

    @Autowired
    private RssFeedManager feedManager;

    @PostMapping("/feeds")
    public List<FeedInfo> feeds() {
        List<RssFeed> feedList = feedManager.listFeeds();
        return feedList.stream().map(feed -> FeedInfo.builder()
                .name(feed.getName())
                .description(feed.getDescription())
                .type(feed.getType())
                .publishDate(feed.getPublishDate())
                .title(feed.getTitle())
                .entryCount(feed.getEntryCount())
                .build()).collect(Collectors.toList());
    }

    @PostMapping("/entries")
    public List<FeedEntryInfo> entries(@RequestParam(value = "feed", required = false) String feed,
                                        @RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "size", required = false) Integer size) {
        if (page == null || page == 0) page = 1;
        if (size == null || size == 0) size = PAGE_SIZE;
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        // 查询条目列表
        Page<RssFeedEntry> entries = feedManager.listPageEntries(feed, pageRequest);
        return entries.stream().map(e -> FeedEntryInfo.builder()
                .feedName(e.getFeedName())
                .title(e.getTitle())
                .link(e.getLink())
                .categories(e.getCategories())
                .author(e.getAuthor())
                .description(e.getDescription())
                .publishedDate(e.getPublishedDate())
                .build()).collect(Collectors.toList());
    }
}
