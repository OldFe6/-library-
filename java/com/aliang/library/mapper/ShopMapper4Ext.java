package com.aliang.library.mapper;

import com.aliang.library.entity.Record4Ext;
import com.aliang.library.entity.Shop4Ext;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShopMapper4Ext {

    long countByConditions(Map<String, Object> conditions);

    int insertSelective(Integer userId, Integer bookId, String buyTime);

    List<Shop4Ext> selectByConditions(Map<String, Object> conditions);
}
