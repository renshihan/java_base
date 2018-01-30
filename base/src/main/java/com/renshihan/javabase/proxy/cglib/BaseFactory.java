package com.renshihan.javabase.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * Created by admin on 2017/8/17.
 */
public class BaseFactory {
    public static <T> T getInstance(MethodInterceptor proxy, Class<T> tClass){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(tClass);     //添加被代理对象
        enhancer.setCallback(proxy);        //添加代理
        return (T) enhancer.create();
    }
}
