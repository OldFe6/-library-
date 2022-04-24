package com.aliang.library.service;


import com.aliang.library.entity.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
    long countUsers(Map<String, Object> conditions);

    List<User> queryUsers(Map<String, Object> conditions);

    User queryUserById(Integer userId);

    User addUser(User user);

    User updateUser(User user);

    User deleteUserById(Integer userId);

    /**
     * 登录业务
     *
     * @param username 用户名
     * @param password 密码
     * @return 查找到的用户 如果没有返回null,如果有,返回对应的对象
     */
    User login(String username, String password);
}
