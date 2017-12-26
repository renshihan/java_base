package com.renshihan.javabase.proxy.cglib;

/**
 * Created by admin on 2017/8/17.
 * 被代理类,target
 */
public class Base {
    public void add(){
        System.out.println("-add()!");
    }
    public  void abc(){
        System.out.println("abc----");
    }
}
