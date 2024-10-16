package com.chenx.springbootmongodb.dao.impl;

import com.chenx.springbootmongodb.dao.RssFeedRepositoryCustom;
import com.chenx.springbootmongodb.pojo.NameValue;
import com.chenx.springbootmongodb.pojo.RssFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class RssFeedRepositoryImpl implements RssFeedRepositoryCustom {
    @Autowired
    private MongoTemplate template;


    @Override
    public void saveStats(List<NameValue> nameValueList) {
        BulkOperations bulkOperations = template.bulkOps(BulkOperations.BulkMode.UNORDERED, RssFeed.class);
        nameValueList.forEach(nv -> {
            Query query = new Query();
            query.addCriteria(Criteria.where(RssFeed.COL_NAME).is(nv.getName()));
            Update update = new Update();
            update.set(RssFeed.COL_ENTRY_COUNT, nv.getValue());
            bulkOperations.updateOne(query, update);
        });
        bulkOperations.execute();
    }
}
