package com.aliang.library.util;

public class Constant {
    //请求头
    public static final String LOGIN = "login";//登录
    public static final String REGISTER = "register";//注册
    public static final String USER_QUERY_CARS = "userQueryCars";//普通查询所有的汽车
    public static final String ADMIN_QUERY_CARS = "adminQueryCars";//普通查询所有的汽车
    public static final String RENT_CAR = "rentCar";//接车
    public static final String RETURN_CAR = "returnCar";//还车
    public static final String QUERY_RECORDS = "queryRecords";//查询记录
    public static final String QUERY_CATEGORYS = "queryCategorys";//查询类别
    public static final String QUERY_BRANDS = "queryBrands";//查询品牌
    public static final String ADD_CAR = "addCar";//添加汽车
    public static final String UPDATE_CAR = "updateCar";//更改汽车

    //执行操作是否成功的返回状态
    public static final String SUCCESS = "1";//成功状态
    public static final String FAILED = "0";//失败状态

    //查询汽车的条件
    public static final String CAR_BY_ID = "1";//同过汽车编号查询
    public static final String ALL_CARS = "5";//查询全部汽车
    public static final String ALL_CARS_BY_PRICE = "2";//按照价格查询
    public static final String CARS_BY_CATEGORY_ID = "3";//按照类别查询
    public static final String CARS_BY_BRAND_ID = "4";//按照品牌查询

    //查询记录的条件
    public static final String ALL_RECORDS = "6";//查询全部记录
    public static final String RECORDS_BY_USER_ID = "7";//查询指定用户记录
    public static final String RECORDS_BY_CAR_ID = "8";//查询指定汽车记录

    //修改汽车的属性
    public static final String RENT = "1";//租金
    public static final String USABLE = "2";//是否可用

    //升序降序
    public static final String ASC = "1";//升序
    public static final String DESC = "2";//降序
}
