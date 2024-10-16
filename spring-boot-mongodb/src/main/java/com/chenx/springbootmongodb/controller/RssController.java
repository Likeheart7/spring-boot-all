package com.chenx.springbootmongodb.controller;

import com.chenx.springbootmongodb.pojo.RssFeedEntry;
import com.chenx.springbootmongodb.service.RssFeedManager;
import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class RssController {
    private static final int DEFAULT_PUBLISH_SIZE = 20;
    private static  final String FEED_TYPE = "rss_2.0";
    private static final String FEED_TITLE = "LIKEH.RSS";
    private static final String FEED_LINK = "http://www.likeheart.com/feed";
    private static final String FEED_DESC = "Basic Aggregation";
    private static final String FEED_ENTRY_CONTENT_TYPE = "text/html";

    @Autowired
    private RssFeedManager feedManager;

    @GetMapping(value = "/rss", produces = MediaType.TEXT_XML_VALUE)
    public String rss() {
        // 执行查询
        List<RssFeedEntry> feedEntries = feedManager.listRecentEntries(DEFAULT_PUBLISH_SIZE);
        // 转换
        SyndFeedImpl outputFeed = new SyndFeedImpl();
        outputFeed.setFeedType(FEED_TYPE);
        outputFeed.setTitle(FEED_TITLE);
        outputFeed.setLink(FEED_LINK);
        outputFeed.setDescription(FEED_DESC);

        ArrayList<SyndEntry> outputEntries = new ArrayList<>();
        for (RssFeedEntry feedEntry : feedEntries) {
            SyndEntryImpl outputEntry = new SyndEntryImpl();
            outputEntry.setTitle(feedEntry.getTitle());
            outputEntry.setLink(feedEntry.getLink());
            outputEntry.setAuthor(feedEntry.getAuthor());
            outputEntry.setPublishedDate(feedEntry.getPublishedDate().toDate());
            SyndContentImpl description = new SyndContentImpl();
            description.setType(FEED_ENTRY_CONTENT_TYPE);
            description.setValue(feedEntry.getDescription());
            outputEntry.setDescription(description);

            // 分类
            List<SyndCategoryImpl> categories = feedEntry.getCategories().stream().map(c -> {
                SyndCategoryImpl category = new SyndCategoryImpl();
                category.setName(c);
                return category;
            }).collect(Collectors.toList());

            outputEntry.setCategories(categories);
            outputEntries.add(outputEntry);
        }

        outputFeed.setEntries(outputEntries);
        SyndFeedOutput syndFeedOutput = new SyndFeedOutput();
        try {
            String feedXmlContext = syndFeedOutput.outputString(outputFeed);
            return feedXmlContext;
        } catch (FeedException e) {
            log.error("error occurs." ,e);
            return "ERROR";
        }
    }

}
