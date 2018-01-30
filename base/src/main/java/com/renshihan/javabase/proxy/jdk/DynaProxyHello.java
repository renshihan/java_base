package com.renshihan.javabase.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by admin on 2017/8/17.
 */
public class DynaProxyHello implements InvocationHandler{

    //调用对象
    private Object proxy;
    //目标对象
    private Object target;
    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
        Object result=null;
        //反射得到调用对象的start方法
        Method start=this.proxy.getClass().getDeclaredMethod("start");
//        //反射执行start方法
        start.invoke(this.proxy);
        //执行要处理对象的原本方法
        result=method.invoke(this.target,objects);
        //反射得到操作者的end 方法
        Method end=this.proxy.getClass().getDeclaredMethod("end");
        //反射执行end方法
        end.invoke(this.proxy);
        return result;
    }
    public <T>T bind(Object target,Object proxy){
        this.target=target;
        this.proxy=proxy;
        return (T)Proxy.newProxyInstance(this.target.getClass().getClassLoader(),this.target.getClass().getInterfaces(),this);
    }
}
