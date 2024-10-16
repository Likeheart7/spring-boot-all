package com.chenx.springbootmongodb.demos;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.URL;

public class RomeEx {
    public static void main(String[] args) throws IOException, FeedException {
        String url = "https://sspai.com/feed";
        SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
        System.out.println(feed.getTitle());
    }
}
