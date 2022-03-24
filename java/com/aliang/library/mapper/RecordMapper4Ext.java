package com.aliang.library.mapper;


import com.aliang.library.entity.Record4Ext;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface RecordMapper4Ext {

    List<Record4Ext> selectByConditions(Map<String, Object> conditions);

    long countByConditions(Map<String, Object> conditions);

    int insertSelective(Integer userId, Integer bookId, String lendDate);

    List<Record4Ext> selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Integer id, Integer bookId,String returnDate);

}
