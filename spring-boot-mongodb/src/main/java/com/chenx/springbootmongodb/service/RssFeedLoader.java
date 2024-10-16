package com.chenx.springbootmongodb.service;

import com.chenx.springbootmongodb.pojo.FeedSource;
import com.chenx.springbootmongodb.pojo.RssFeed;
import com.chenx.springbootmongodb.pojo.RssFeedEntry;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class RssFeedLoader {

    static {
        // 设置默认的SSLSocketFactory、HostNameVerifier实例
        HttpsURLConnection.setDefaultHostnameVerifier(noopHostnameVerifier());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory());
    }
    private static SSLSocketFactory sslSocketFactory() {
        try{
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            }, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static HostnameVerifier noopHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        };
    }

    public Pair<RssFeed, List<RssFeedEntry>> loadFeed(FeedSource feedSource) {
        log.info("start to load feed '{}' from {}", feedSource.getName(), feedSource.getUrl());
        try {
            URL feedUrl = new URL(feedSource.getUrl());
            SyndFeedInput syndFeedInput = new SyndFeedInput();
            // 解析URL内容
            SyndFeed syndFeed = syndFeedInput.build(new XmlReader(feedUrl));

            RssFeed rssFeed = new RssFeed();
            rssFeed.setName(feedSource.getName());
            rssFeed.setUrl(feedSource.getUrl());
            // 频道类型
            rssFeed.setType(syndFeed.getFeedType());
            // 频道日期
            rssFeed.setPublishDate(wrapPublishedDate(syndFeed.getPublishedDate()));
            // 频道标题
            rssFeed.setTitle(syndFeed.getTitle());
            // 频道描述
            rssFeed.setDescription(syndFeed.getDescription());
            Stream<RssFeedEntry> iFeedEntry = syndFeed.getEntries().stream().map(e -> {
                SyndEntry entry = (SyndEntry) e;
                RssFeedEntry rssFeedEntry = new RssFeedEntry();
                rssFeedEntry.setFeedName(feedSource.getName());
                // 条目链接
                rssFeedEntry.setLink(entry.getLink());
                // 条目标题
                rssFeedEntry.setTitle(entry.getTitle());
                // 条目摘要
                rssFeedEntry.setDescription(entry.getDescription().getValue());
                // 条目日期
                rssFeedEntry.setPublishedDate(wrapPublishedDate(entry.getPublishedDate()));
                // 条目作者
                rssFeedEntry.setAuthor(entry.getAuthor());
                // 分类
                Stream<String> iCategory = entry.getCategories().stream().map(c -> ((SyndCategory) c).getName());
                rssFeedEntry.setCategories(iCategory.collect(Collectors.toList()));
                return rssFeedEntry;
            });
            List<RssFeedEntry> rssFeedEntries = iFeedEntry.collect(Collectors.toList());
            log.info("load feed '{}' finished, title: {}, type: {}, entryCount: {}", rssFeed.getName(), rssFeed.getTitle(), rssFeed.getType(), rssFeedEntries.size());
            return Pair.with(rssFeed, rssFeedEntries);


        } catch (IOException | FeedException e) {
            log.error("error occurs", e);
            return null;
        }
    }

    private DateTime wrapPublishedDate(Date date) {
        return date != null ? new DateTime(date) : new DateTime();
    }
}
