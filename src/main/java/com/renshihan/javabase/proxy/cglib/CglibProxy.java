package com.renshihan.javabase.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by admin on 2017/8/17.
 */
public class CglibProxy implements MethodInterceptor{

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("--before---");
        methodProxy.invokeSuper(o,objects);

        System.out.println("---after------");
        return null;
    }

}
