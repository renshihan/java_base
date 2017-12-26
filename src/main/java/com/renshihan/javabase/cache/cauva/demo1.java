package com.renshihan.javabase.cache.cauva;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/6/20.
 * 堆缓存
 */
public class demo1 {
    /**
     * 初始化Cache
     * */
    public void test(){
        Cache<String,String> myCache= CacheBuilder.newBuilder()
                .concurrencyLevel(4)        //并发级别 越大并发能力越强
                .expireAfterWrite(10, TimeUnit.SECONDS) //设置TTL,缓存数据在给定的时间内没有写（创建/覆盖）时，则被回收，即定期会回收缓存数据。
                .maximumSize(10000) //设置缓存的容量，当超出maximumSize，使用LRU进行缓存回收
                .build();

    }

}
