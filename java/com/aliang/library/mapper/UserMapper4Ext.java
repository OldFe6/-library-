package com.aliang.library.mapper;


import com.aliang.library.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper4Ext {

    List<User> selectByConditions(Map<String, Object> conditions);

    long countByConditions(Map<String, Object> conditions);

    // 买书
    int updateByPrimaryKey(Integer id);
}
