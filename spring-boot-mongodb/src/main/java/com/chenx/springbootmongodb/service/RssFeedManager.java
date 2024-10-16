package com.chenx.springbootmongodb.service;

import com.chenx.springbootmongodb.config.FeedSourceConfig;
import com.chenx.springbootmongodb.dao.RssFeedEntryRepository;
import com.chenx.springbootmongodb.dao.RssFeedRepository;
import com.chenx.springbootmongodb.pojo.FeedSource;
import com.chenx.springbootmongodb.pojo.NameValue;
import com.chenx.springbootmongodb.pojo.RssFeed;
import com.chenx.springbootmongodb.pojo.RssFeedEntry;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class RssFeedManager {
    @Autowired
    private RssFeedLoader rssFeedLoader;

    @Autowired
    private FeedSourceConfig feedSourceConfig;
    @Autowired
    private RssFeedRepository rssFeedRepository;

    @Autowired
    private RssFeedEntryRepository rssFeedEntryRepository;
    private Page<RssFeedEntry> byOrderByPublishedDateDesc;

    /**
     * 获取数据
     */
    public void loadFeeds() {
        log.info("load all feed sources.");
        // 加载feed条目
        for (FeedSource feedSource : feedSourceConfig.getFeedSources()) {
            Pair<RssFeed, List<RssFeedEntry>> feedListPair = rssFeedLoader.loadFeed(feedSource);
            if (feedListPair == null) {
                log.warn("load feed '{}' failed." ,feedSource.getName());
                continue;
            }
            mergeSave(feedListPair.getValue0(), feedListPair.getValue1());
        }

        // 处理完成，更新统计
        updateStat();
    }

    private void updateStat() {
        AggregationResults<NameValue> statResult = rssFeedEntryRepository.statCounter();
        List<NameValue> nameValues = statResult.getMappedResults();
        log.info("update feed stats: {}", nameValues);
        rssFeedRepository.saveStats(nameValues);
    }

    private void mergeSave(RssFeed feed, List<RssFeedEntry> feedEntries) {
        // 持久化feed
        if (rssFeedRepository.countByName(feed.getName()) == 0) {
            rssFeedRepository.save(feed);
            log.info("feed '{}' saved.", feed.getName());
        } else {
            log.info("feed '{}' has exists.", feed.getName());
        }
        // 持久化条目
        feedEntries.forEach(e -> {
            if (rssFeedEntryRepository.countByFeedNameAndLink(e.getFeedName(), e.getLink()) == 0) {
                rssFeedEntryRepository.save(e);
                log.info("feed entry '{}' [{}]@{} saved.", e.getFeedName(), e.getTitle(), e.getLink());
            } else {
                log.info("feed entry '{}' [{}]@{} has exists..", e.getFeedName(), e.getTitle(), e.getLink());
            }
        });
    }

    /**
     * 获取最近的条目
     */
    public List<RssFeedEntry> listRecentEntries(int limit) {
        Assert.isTrue(limit > 0, "limit must be larger than zero");
        Page<RssFeedEntry> feedEntries = rssFeedEntryRepository.findByOrderByPublishedDateDesc(PageRequest.of(0, limit));
        return feedEntries.getContent();
    }


    /**
     * 获取Feed列表
     */
    public List<RssFeed> listFeeds() {
        return rssFeedRepository.findAll();
    }

    /**
     * 根据分页获得条目
     */
    public Page<RssFeedEntry> listPageEntries(String feedName, PageRequest pageRequest) {
        Assert.notNull(pageRequest, "pageRequest required.");
        Page<RssFeedEntry> feedEntries;
        if (StringUtils.isEmpty(feedName)) {
            feedEntries = rssFeedEntryRepository.findByOrderByPublishedDateDesc(pageRequest);
        } else {
            feedEntries = rssFeedEntryRepository.findByFeedNameOrderByPublishedDateDesc(feedName, pageRequest);
        }
        return feedEntries;

    }
}
