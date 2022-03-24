package com.aliang.library.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * 简单封装Fastjson，实现JSON String<->Java Object的转化
 *
 * @author Alain
 */
public class JsonUtil {


    /**
     * 将对象转化为JSON字符串
     *
     * @param object 需要转化为字符串的对象
     * @return
     */
    public static String toJson(Object object) {
        return JSONObject.toJSONString(object);
    }

    /**
     * 将JSON字符串转化为指定类型的对象
     *
     * @param jsonString 需要转化为对象的字符串
     * @param clazz      转化而来对象的类型
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        return JSONObject.parseObject(jsonString, clazz);
    }

    /**
     * 将JSON字符串转化为JSON对象
     *
     * @param jsonString 需要转化为对象的字符串
     * @return
     */
    public static JSONObject fromJson(String jsonString) {
        return JSONObject.parseObject(jsonString);
    }

    /**
     * 获取JSONObject在指定JSON Path位置上的字段值
     *
     * @param jsonObject 所要获取字段值的JSONObject
     * @param path       指定的JSON Path
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T valueByPath(JSONObject jsonObject, String path, Class<T> clazz) {
        Object value = JSONPath.eval(jsonObject, path);
        if (value == null) {
            return null;
        }
        if (clazz != null && clazz.isInstance(value)) {
            return clazz.cast(value);
        }
        throw new IllegalArgumentException(String.format(
                "invalid path[%s] for jsonObject[%s] and clazz[%s]",
                path, jsonObject, clazz));
    }

    /**
     * 获取JSONObject在指定JSON Path位置上的字符串值
     *
     * @param jsonObject 所要获取字段值的JSONObject
     * @param path       指定的JSON Path
     * @return
     */
    public static String stringByPath(JSONObject jsonObject, String path) {
        return valueByPath(jsonObject, path, String.class);
    }
}
