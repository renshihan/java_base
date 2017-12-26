package com.renshihan.javabase.proxy.jingtai;

/**
 * Created by admin on 2017/8/17.
 */
public class Test1 {
    public static void main(String[] args) {
        IHello hello=new HelloProxy(new Hello());
        hello.sayHello("你好");
    }
}
