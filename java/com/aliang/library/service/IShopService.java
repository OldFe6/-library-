package com.aliang.library.service;

import com.aliang.library.entity.Shop;
import com.aliang.library.entity.Shop4Ext;

import java.util.List;
import java.util.Map;

public interface IShopService {

    long countRecords(Map<String, Object> conditions);

    List<Shop4Ext> queryShopRecords(Map<String, Object> queryConditions);

    Shop buyBook(Integer userId, Integer bookId);
}
