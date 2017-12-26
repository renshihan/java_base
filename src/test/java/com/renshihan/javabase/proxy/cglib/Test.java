package com.renshihan.javabase.proxy.cglib;

/**
 * Created by admin on 2017/8/17.
 */
public class Test {
    public static void main(String[] args) {
        CglibProxy proxy=new CglibProxy();
        Base base=BaseFactory.getInstance(proxy,Base.class);
        base.add();
        System.out.println("==================");
        base.abc();
    }
}
