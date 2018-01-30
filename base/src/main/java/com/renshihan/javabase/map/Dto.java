package com.renshihan.javabase.map;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/8/18.
 */
public interface Dto extends Map<String,Object> {
    /**
     * 通过键，返回一个字符串的值
     * */
    String getString(String key);
    /**
     * 通过键，返回一个数组类型的对象
     * @param key
     * @return the list
     * */
    <E> List<E> getList(String key);
}
