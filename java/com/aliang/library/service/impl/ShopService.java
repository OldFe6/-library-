package com.aliang.library.service.impl;

import com.aliang.library.entity.Book;
import com.aliang.library.entity.Shop;
import com.aliang.library.entity.Shop4Ext;
import com.aliang.library.entity.User;
import com.aliang.library.mapper.*;
import com.aliang.library.service.IShopService;
import com.aliang.library.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ShopService implements IShopService {

    @Autowired
    private ShopMapper4Ext shopMapper4Ext;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookMapper4Ext bookMapper4Ext;

    @Autowired
    private UserMapper4Ext userMapper4Ext;

    @Override
    public long countRecords(Map<String, Object> conditions) {
        return shopMapper4Ext.countByConditions(conditions);
    }

    @Override
    public List<Shop4Ext> queryShopRecords(Map<String, Object> queryConditions) {
        return shopMapper4Ext.selectByConditions(queryConditions);
    }

    @Override
    @Transactional //事务
    public Shop buyBook(Integer userId, Integer bookId) {
        Shop shop = null;
        int id;
        Book book = bookMapper.selectByPrimaryKey(bookId);
        // 查询当前图书是否可租赁
        if (book != null && book.getUsable() == 1) {
            // 如果可以租赁，修改图书表
            int rows = bookMapper4Ext.updateByPrimaryKey(bookId);
            if (rows == 1) {// 返回修改的记录行数为1，说明修改图书成功
                // 添加记录
                id = shopMapper4Ext.insertSelective(userId, bookId, Util.today());
                shop = shopMapper.selectByPrimaryKey(id);
            }
        }
        return shop;
    }

//    @Override
//    @Transactional //事务
//    public Shop buyBook(Integer userId, Integer bookId, String price, String balance) {
//        Shop shop = null;
//        int id;
//        Book book = bookMapper.selectByPrimaryKey(bookId);
//
//        BigDecimal bookPrice = new BigDecimal("price1");
//        BigDecimal userBalance = new BigDecimal("balance1");
//        userBalance.subtract(bookPrice);
//        if (book != null && book.getUsable() == 1) {
//
//            int rows = bookMapper4Ext.updateByPrimaryKey(bookId);
//            if (rows == 1) {// 返回修改的记录行数为1，说明修改图书成功
//                // 添加记录
//                id = shopMapper4Ext.insertSelective(userId, bookId, Util.today());
//                shop = shopMapper.selectByPrimaryKey(id);
//            }
//        }
//        return shop;
//    }


}
