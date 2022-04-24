package com.aliang.library.service.impl;

import com.aliang.library.entity.*;
import com.aliang.library.mapper.*;
import com.aliang.library.service.IRecordService;
import com.aliang.library.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RecordService implements IRecordService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookMapper4Ext bookMapper4Ext;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private RecordMapper4Ext recordMapper4Ext;

    @Autowired
    private UserMapper4Ext userMapper4Ext;

    @Autowired
    private ShopMapper4Ext shopMapper4Ext;

    @Autowired
    private ShopMapper shopMapper;


    @Override
    public long countRecords(Map<String, Object> conditions) {
        return recordMapper4Ext.countByConditions(conditions);
    }

    @Override
    public List<Record4Ext> queryRecords(Map<String, Object> queryConditions) {
        return recordMapper4Ext.selectByConditions(queryConditions);
    }

    @Override
    @Transactional //事务
    public Record lendBook(Integer userId, Integer bookId) {
        Record record = null;
        int id;
        Book book = bookMapper.selectByPrimaryKey(bookId);
        // 查询当前图书是否可租赁
        if (book != null && book.getStatus() == 1 && book.getUsable() == 1) {
            // 如果可以租赁，修改图书表
            int rows = bookMapper4Ext.updateByPrimaryKey(bookId);
            if (rows == 1) {// 返回修改的记录行数为1，说明修改图书成功
                // 添加记录
                id = recordMapper4Ext.insertSelective(userId, bookId, Util.today());
                record = recordMapper.selectByPrimaryKey(id);
            }
        }
        return record;
    }

    @Override
    @Transactional
    public Record returnBook(Integer Id, Integer bookId) {
        Record record = null;
        int id;
        Book book = bookMapper.selectByPrimaryKey(bookId);
        // 查询当前图书是否可租赁
        if (book != null) {
            // 如果可以还车，修改图书表
            int rows = bookMapper4Ext.updateByPrimaryKeyStatus(bookId);
            if (rows == 1) {    // 返回修改的记录行数为1，说明修改图书成功
                id = recordMapper4Ext.updateByPrimaryKeySelective(Id, bookId, Util.today());
                record = recordMapper.selectByPrimaryKey(id);

//                DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
//
//                try {
//                    Date star = dft.parse(list.get(0).getLendDate());//获取当前记录的借车时间
//                    Date endDay = dft.parse(Util.today());
//                    Long starTime = star.getTime();//开始时间
//                    Long endTime = endDay.getTime();//结束时间
//                    Long num = (endTime - starTime) / 24 / 60 / 60 / 1000;//时间差
//
//                    Float rent = book.getRent();//获取当前记录表的图书每日租金
//                    if (num == 0) {
//                        double payment = rent * (num + 1);//算钱 每日租金*租借天数
//                        // 修改记录
//                        recordMapper4Ext.updateByPrimaryKeySelective(Id, payment, Util.today());
//                        record = recordMapper.selectByPrimaryKey(Id);
//                    } else {
//                        double payment = rent * num;//算钱 每日租金*租借天数
//                        // 修改记录
//                        recordMapper4Ext.updateByPrimaryKeySelective(Id, payment, Util.today());
//                        record = recordMapper.selectByPrimaryKey(Id);
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }

            }
        }
        return record;
    }

}
