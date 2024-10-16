package com.chenx.springbootmongodb.job;

import com.chenx.springbootmongodb.service.RssFeedManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RssJob {
    public static final long FIX_INTERVAL = 30 * 60 * 1000L;
    public static final long INIT_DELAY = 5 * 1000L;

    @Autowired
    private RssFeedManager feedManager;

    /**
     * 定时调度扫描
     */
    @Scheduled(initialDelay = INIT_DELAY, fixedDelay = FIX_INTERVAL)
    public void onFixDelay() {
        feedManager.loadFeeds();
    }
}
