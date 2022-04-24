package com.aliang.library.service;

import com.aliang.library.entity.Record;
import com.aliang.library.entity.Record4Ext;
import com.aliang.library.entity.Shop;
import com.aliang.library.entity.Shop4Ext;

import java.util.List;
import java.util.Map;

public interface IRecordService {
    long countRecords(Map<String, Object> conditions);

    List<Record4Ext> queryRecords(Map<String, Object> queryConditions);



    Record lendBook(Integer userId, Integer bookId);



    Record returnBook(Integer Id, Integer bookId);
}
