package com.chenx.springbootmongodb.dao;

import com.chenx.springbootmongodb.pojo.RssFeed;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RssFeedRepository extends MongoRepository<RssFeed, String>, RssFeedRepositoryCustom {
    long countByName(String name);

    List<RssFeed> findByNameIn(List<String> names);
}
