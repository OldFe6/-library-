package com.aliang.library.mapper;

import com.aliang.library.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper4Ext {

    List<Book> selectByConditions(Map<String, Object> conditions);

    long countByConditions(Map<String, Object> conditions);

    int updateByPrimaryKey(Integer id);

    int updateByPrimaryKeyStatus(Integer id);
}
