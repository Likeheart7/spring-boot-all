package com.chenx.springbootmongodb.dao;

import com.chenx.springbootmongodb.pojo.NameValue;
import com.chenx.springbootmongodb.pojo.RssFeed;
import com.chenx.springbootmongodb.pojo.RssFeedEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RssFeedEntryRepository extends MongoRepository<RssFeedEntry, String>{
    long countByFeedNameAndLink(String feedName, String link);

    Page<RssFeedEntry> findByOrderByPublishedDateDesc(Pageable pageable);


    @Aggregation("{ $group: {id: $feedName, value: {$sum : 1}}}")
    AggregationResults<NameValue> statCounter();

}
