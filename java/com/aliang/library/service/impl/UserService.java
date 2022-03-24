package com.aliang.library.service.impl;


import com.aliang.library.entity.User;
import com.aliang.library.entity.UserExample;
import com.aliang.library.mapper.UserMapper;
import com.aliang.library.mapper.UserMapper4Ext;
import com.aliang.library.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMapper4Ext userMapper4Ext;

    @Override
    public long countUsers(Map<String, Object> conditions) {
        return userMapper4Ext.countByConditions(conditions);
    }

    @Override
    public List<User> queryUsers(Map<String, Object> conditions) {
        return userMapper4Ext.selectByConditions(conditions);
    }


    @Override
    public User queryUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User addUser(User user) {
        int counts = userMapper.insert(user);
        if (counts == 0) {
            return null;
        }
        return userMapper.selectByPrimaryKey(user.getId());
    }

    @Override
    public User updateUser(User user) {
        int counts = userMapper.updateByPrimaryKey(user);
        if (counts == 0) {
            return null;
        }
        return userMapper.selectByPrimaryKey(user.getId());
    }

    @Override
    public User deleteUserById(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return null;
        }
        userMapper.deleteByPrimaryKey(userId);
        return user;
    }

    @Override
    public User login(String username, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList == null || userList.size() == 0) {
            return null;
        }
        if (userList.size() > 1) {
            throw new RuntimeException(String.format("找到多个用户, username[%s]", username));
        }
        return userList.get(0);
    }
}
