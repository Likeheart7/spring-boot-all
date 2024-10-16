package com.chenx.springbootmongodb.dao;

import com.chenx.springbootmongodb.pojo.NameValue;

import java.util.List;

public interface RssFeedRepositoryCustom {
    void saveStats(List<NameValue> nameValueList);
}
