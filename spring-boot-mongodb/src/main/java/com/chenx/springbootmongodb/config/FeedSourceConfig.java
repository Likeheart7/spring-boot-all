package com.chenx.springbootmongodb.config;

import com.chenx.springbootmongodb.pojo.FeedSource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@EnableConfigurationProperties(FeedSourceConfig.RssFeedSourcesProperties.class)
@Slf4j
public class FeedSourceConfig {
    @Autowired
    private RssFeedSourcesProperties feedSourcesProperties;

    @PostConstruct
    void init() {
        log.info("rss feed source: {}", feedSourcesProperties);
    }

    public List<FeedSource> getFeedSources() {
        return feedSourcesProperties.getSources();
    }

    @ConfigurationProperties("rss.feed")
    @Getter
    @Setter
    public static class RssFeedSourcesProperties {
        private List<FeedSource> sources;
    }
}
