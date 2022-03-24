package com.aliang.library.controller;

import com.aliang.library.common.PageNavUtil;
import com.aliang.library.common.model.MPageNav;
import com.aliang.library.entity.Record4Ext;
import com.aliang.library.entity.Shop4Ext;
import com.aliang.library.model.MRecord;
import com.aliang.library.model.MRecordSearch;
import com.aliang.library.model.MShop;
import com.aliang.library.model.MShopSearch;
import com.aliang.library.service.IRecordService;
import com.aliang.library.service.IShopService;
import com.aliang.library.util.Util;
import com.aliang.library.vo.RecordRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RecordController {

    @Autowired
    private IRecordService recordService;

    @Autowired
    private IShopService shopService;

    @RequestMapping(value = "/recordList", method = RequestMethod.GET)
    public String recordList(HttpServletRequest request,
                             RecordRequest recordRequest,
                             Model model) {
        // 根据图书查询条件的参数，生成用于分页的基准url，也就是baseUrl。
        //这个baseUrl会用于生成分页导航中的a标签的href属性，也就是超链接的值。
        // 同时，还会生成数据库SQL查询的条件清单，也就是queryConditions
        List<String> params = new ArrayList<>();
        Map<String, Object> queryConditions = new HashMap<>();
        if (StringUtils.isNotEmpty(recordRequest.getBookId())) {
            params.add(String.format("bookId = %s", recordRequest.getBookId()));
            queryConditions.put("bookId", recordRequest.getBookId());
        }
        if (StringUtils.isNotEmpty(recordRequest.getBookName())) {
            params.add(String.format("bookName = %s", recordRequest.getBookName()));
            queryConditions.put("bookName", recordRequest.getBookName());
        }
        if (StringUtils.isNotEmpty(recordRequest.getUserId())) {
            params.add(String.format("userId = %s", recordRequest.getUserId()));
            queryConditions.put("userId", recordRequest.getUserId());
        }
//        HttpSession session = request.getSession(false);
//        MUser mmUser = (MUser) session.getAttribute("mUser");
//        if ("管理员".equals(mmUser.getType())) {
//            queryConditions.add(new QueryCondition("type", "1"));
//        }

        String queryParams = String.join("&", params);
        String baseUrl = "recordList";
        if (StringUtils.isNotEmpty(queryParams)) {
            baseUrl = baseUrl + "?" + queryParams;
        }

        //根据指定的条件，查询符合条件的所有的图书的总数。
        //就相当于 select count(*) from t_car where ${queryConditions}
        //获取这个总数的目的，是为了知道分页导航的最大页数是多少，也就是分页导航中尾页的索引号
        int records = (int) recordService.countRecords(queryConditions);

        //根据基准url baseUrl，查询页面索引号，每页的记录数，总记录数 这4个参数，
        //生成分页导航的Model数据，也就是MPageNav
        MPageNav mPageNav = PageNavUtil.genMPageNav(baseUrl,
                recordRequest.getPage(), recordRequest.getPageSize(), records);
        model.addAttribute("mPageNav", mPageNav);

        //根据查询条件queryConditions，以及分页导航中指定页面需要查询的数据范围，也就是limit和offset
        //查询出页面需要的数据
        queryConditions.put("limit", Integer.parseInt(mPageNav.limit));
        queryConditions.put("offset", Integer.parseInt(mPageNav.offset));
        List<Record4Ext> recordList = recordService.queryRecords(queryConditions);
        //查询出图书列表数据后，将其按照JSP页面需要的字段，转化为Model数据

        List<MRecord> mRecords = new ArrayList<>(recordList.size());
        for (Record4Ext record : recordList) {
            MRecord mRecord = new MRecord();
            mRecord.setId(String.valueOf(record.getId()));
            mRecord.setBookId(String.valueOf(record.getBookId()));
            mRecord.setBookName(String.valueOf(record.getBookName()));
            mRecord.setUserId(String.valueOf(record.getUserId()));
            mRecord.setUsername(String.valueOf(record.getUsername()));
            mRecord.setAuthor(String.valueOf(record.getAuthor()));
            mRecord.setPrice(String.valueOf(record.getPrice()));
//            mRecord.setRent(String.valueOf(record.getRent()));
            mRecord.setLendDate(Util.today());
            mRecord.setReturnDate(String.valueOf(record.getReturnDate()));
            mRecords.add(mRecord);
        }
        // 将model数据放入request，之后在JSP中，能够通过requestScope访问到model数据
        request.setAttribute("mRecords", mRecords);

        // MCarSearch用于保存查询的条件，将其按照查询传入的值，填写到搜索表单中。
        MRecordSearch mRecordSearch = new MRecordSearch(recordRequest.getBookId(), recordRequest.getBookName(), recordRequest.getUserId());
        request.setAttribute("mRecordSearch", mRecordSearch);

        return "recordList";
    }

    @RequestMapping(value = "/personalRecord", method = RequestMethod.GET)
    public String personalRecord(HttpServletRequest request,
                             RecordRequest recordRequest,
                             Model model) {
        // 根据图书查询条件的参数，生成用于分页的基准url，也就是baseUrl。
        //这个baseUrl会用于生成分页导航中的a标签的href属性，也就是超链接的值。
        // 同时，还会生成数据库SQL查询的条件清单，也就是queryConditions
        List<String> params = new ArrayList<>();
        Map<String, Object> queryConditions = new HashMap<>();
        if (StringUtils.isNotEmpty(recordRequest.getBookId())) {
            params.add(String.format("bookId = %s", recordRequest.getBookId()));
            queryConditions.put("bookId", recordRequest.getBookId());
        }
        if (StringUtils.isNotEmpty(recordRequest.getBookName())) {
            params.add(String.format("bookName = %s", recordRequest.getBookName()));
            queryConditions.put("bookName", recordRequest.getBookName());
        }
        if (StringUtils.isNotEmpty(recordRequest.getUserId())) {
            params.add(String.format("userId = %s", recordRequest.getUserId()));
            queryConditions.put("userId", recordRequest.getUserId());
        }

        String queryParams = String.join("&", params);
        String baseUrl = "personalRecord";
        if (StringUtils.isNotEmpty(queryParams)) {
            baseUrl = baseUrl + "?" + queryParams;
        }

        //根据指定的条件，查询符合条件的所有的图书的总数。
        //就相当于 select count(*) from t_car where ${queryConditions}
        //获取这个总数的目的，是为了知道分页导航的最大页数是多少，也就是分页导航中尾页的索引号
        int records = (int) recordService.countRecords(queryConditions);

        //根据基准url baseUrl，查询页面索引号，每页的记录数，总记录数 这4个参数，
        //生成分页导航的Model数据，也就是MPageNav
        MPageNav mPageNav = PageNavUtil.genMPageNav(baseUrl,
                recordRequest.getPage(), recordRequest.getPageSize(), records);
        model.addAttribute("mPageNav", mPageNav);

        //根据查询条件queryConditions，以及分页导航中指定页面需要查询的数据范围，也就是limit和offset
        //查询出页面需要的数据
        queryConditions.put("limit", Integer.parseInt(mPageNav.limit));
        queryConditions.put("offset", Integer.parseInt(mPageNav.offset));
        List<Record4Ext> recordList = recordService.queryRecords(queryConditions);
        //查询出图书列表数据后，将其按照JSP页面需要的字段，转化为Model数据

        List<MRecord> mRecords = new ArrayList<>(recordList.size());
        for (Record4Ext record : recordList) {
            MRecord mRecord = new MRecord();
            mRecord.setId(String.valueOf(record.getId()));
            mRecord.setBookId(String.valueOf(record.getBookId()));
            mRecord.setBookName(String.valueOf(record.getBookName()));
            mRecord.setUserId(String.valueOf(record.getUserId()));
            mRecord.setUsername(String.valueOf(record.getUsername()));
            mRecord.setAuthor(String.valueOf(record.getAuthor()));
            mRecord.setPrice(String.valueOf(record.getPrice()));
            mRecord.setLendDate(Util.today());
            mRecord.setReturnDate(String.valueOf(record.getReturnDate()));
            mRecords.add(mRecord);
        }
        // 将model数据放入request，之后在JSP中，能够通过requestScope访问到model数据
        request.setAttribute("mRecords", mRecords);

        // MCarSearch用于保存查询的条件，将其按照查询传入的值，填写到搜索表单中。
        MRecordSearch mRecordSearch = new MRecordSearch(recordRequest.getBookId(), recordRequest.getBookName(), recordRequest.getUserId());
        request.setAttribute("mRecordSearch", mRecordSearch);

        return "personalRecord";
    }

    @RequestMapping(value = "/shoppingRecord", method = RequestMethod.GET)
    public String shoppingRecord(HttpServletRequest request,
                             RecordRequest recordRequest,
                             Model model) {
        // 根据图书查询条件的参数，生成用于分页的基准url，也就是baseUrl。
        //这个baseUrl会用于生成分页导航中的a标签的href属性，也就是超链接的值。
        // 同时，还会生成数据库SQL查询的条件清单，也就是queryConditions
        List<String> params = new ArrayList<>();
        Map<String, Object> queryConditions = new HashMap<>();
        if (StringUtils.isNotEmpty(recordRequest.getBookId())) {
            params.add(String.format("bookId = %s", recordRequest.getBookId()));
            queryConditions.put("bookId", recordRequest.getBookId());
        }
        if (StringUtils.isNotEmpty(recordRequest.getBookName())) {
            params.add(String.format("bookName = %s", recordRequest.getBookName()));
            queryConditions.put("bookName", recordRequest.getBookName());
        }
        if (StringUtils.isNotEmpty(recordRequest.getUserId())) {
            params.add(String.format("userId = %s", recordRequest.getUserId()));
            queryConditions.put("userId", recordRequest.getUserId());
        }
        String queryParams = String.join("&", params);
        String baseUrl = "shoppingRecord";
        if (StringUtils.isNotEmpty(queryParams)) {
            baseUrl = baseUrl + "?" + queryParams;
        }

        //根据指定的条件，查询符合条件的所有的图书的总数。
        //就相当于 select count(*) from t_car where ${queryConditions}
        //获取这个总数的目的，是为了知道分页导航的最大页数是多少，也就是分页导航中尾页的索引号
        int records = (int) shopService.countRecords(queryConditions);

        //根据基准url baseUrl，查询页面索引号，每页的记录数，总记录数 这4个参数，
        //生成分页导航的Model数据，也就是MPageNav
        MPageNav mPageNav = PageNavUtil.genMPageNav(baseUrl,
                recordRequest.getPage(), recordRequest.getPageSize(), records);
        model.addAttribute("mPageNav", mPageNav);

        //根据查询条件queryConditions，以及分页导航中指定页面需要查询的数据范围，也就是limit和offset
        //查询出页面需要的数据
        queryConditions.put("limit", Integer.parseInt(mPageNav.limit));
        queryConditions.put("offset", Integer.parseInt(mPageNav.offset));
        List<Shop4Ext> recordList = shopService.queryShopRecords(queryConditions);
        //查询出图书列表数据后，将其按照JSP页面需要的字段，转化为Model数据

        List<MShop> mShops = new ArrayList<>(recordList.size());
        for (Shop4Ext shop : recordList) {
            MShop mShop = new MShop();
            mShop.setId(String.valueOf(shop.getId()));
            mShop.setBookId(String.valueOf(shop.getBookId()));
            mShop.setBookName(String.valueOf(shop.getBookName()));
            mShop.setUserId(String.valueOf(shop.getUserId()));
            mShop.setUsername(String.valueOf(shop.getUsername()));
            mShop.setPrice(String.valueOf(shop.getPrice()));
            mShop.setBookCount(String.valueOf(shop.getBookCount()));
            mShop.setAuthor(String.valueOf(shop.getAuthor()));
            mShop.setBuyTime(Util.today());
            mShops.add(mShop);
        }
        // 将model数据放入request，之后在JSP中，能够通过requestScope访问到model数据
        request.setAttribute("mShops", mShops);

        // MCarSearch用于保存查询的条件，将其按照查询传入的值，填写到搜索表单中。
        MShopSearch mRecordSearch = new MShopSearch(recordRequest.getBookId(), recordRequest.getBookName(), recordRequest.getUserId());
        request.setAttribute("mRecordSearch", mRecordSearch);

        return "shoppingRecord";
    }


    @RequestMapping(value = "/myShopping", method = RequestMethod.GET)
    public String myShopping(HttpServletRequest request,
                                 RecordRequest recordRequest,
                                 Model model) {
        // 根据图书查询条件的参数，生成用于分页的基准url，也就是baseUrl。
        //这个baseUrl会用于生成分页导航中的a标签的href属性，也就是超链接的值。
        // 同时，还会生成数据库SQL查询的条件清单，也就是queryConditions
        List<String> params = new ArrayList<>();
        Map<String, Object> queryConditions = new HashMap<>();
        if (StringUtils.isNotEmpty(recordRequest.getBookId())) {
            params.add(String.format("bookId = %s", recordRequest.getBookId()));
            queryConditions.put("bookId", recordRequest.getBookId());
        }
        if (StringUtils.isNotEmpty(recordRequest.getBookName())) {
            params.add(String.format("bookName = %s", recordRequest.getBookName()));
            queryConditions.put("bookName", recordRequest.getBookName());
        }
        if (StringUtils.isNotEmpty(recordRequest.getUserId())) {
            params.add(String.format("userId = %s", recordRequest.getUserId()));
            queryConditions.put("userId", recordRequest.getUserId());
        }

        String queryParams = String.join("&", params);
        String baseUrl = "myShopping";
        if (StringUtils.isNotEmpty(queryParams)) {
            baseUrl = baseUrl + "?" + queryParams;
        }

        //根据指定的条件，查询符合条件的所有的图书的总数。
        //就相当于 select count(*) from t_car where ${queryConditions}
        //获取这个总数的目的，是为了知道分页导航的最大页数是多少，也就是分页导航中尾页的索引号
        int records = (int) shopService.countRecords(queryConditions);

        //根据基准url baseUrl，查询页面索引号，每页的记录数，总记录数 这4个参数，
        //生成分页导航的Model数据，也就是MPageNav
        MPageNav mPageNav = PageNavUtil.genMPageNav(baseUrl,
                recordRequest.getPage(), recordRequest.getPageSize(), records);
        model.addAttribute("mPageNav", mPageNav);

        //根据查询条件queryConditions，以及分页导航中指定页面需要查询的数据范围，也就是limit和offset
        //查询出页面需要的数据
        queryConditions.put("limit", Integer.parseInt(mPageNav.limit));
        queryConditions.put("offset", Integer.parseInt(mPageNav.offset));
        List<Shop4Ext> recordList = shopService.queryShopRecords(queryConditions);
        //查询出图书列表数据后，将其按照JSP页面需要的字段，转化为Model数据

        List<MShop> mShops = new ArrayList<>(recordList.size());
        for (Shop4Ext record : recordList) {
            MShop mShop = new MShop();
            mShop.setId(String.valueOf(record.getId()));
            mShop.setBookId(String.valueOf(record.getBookId()));
            mShop.setBookName(String.valueOf(record.getBookName()));
            mShop.setUserId(String.valueOf(record.getUserId()));
            mShop.setUsername(String.valueOf(record.getUsername()));
            mShop.setAuthor(String.valueOf(record.getAuthor()));
            mShop.setPrice(String.valueOf(record.getPrice()));
            mShop.setBuyTime(Util.today());
            mShops.add(mShop);
        }
        // 将model数据放入request，之后在JSP中，能够通过requestScope访问到model数据
        request.setAttribute("mShops", mShops);

        // MCarSearch用于保存查询的条件，将其按照查询传入的值，填写到搜索表单中。
        MShopSearch mShopSearch = new MShopSearch(recordRequest.getBookId(), recordRequest.getBookName(), recordRequest.getUserId());
        request.setAttribute("mShopSearch", mShopSearch);

        return "myShopping";
    }


}
