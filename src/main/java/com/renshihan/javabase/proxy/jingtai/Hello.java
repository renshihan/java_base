package com.renshihan.javabase.proxy.jingtai;

/**
 * Created by admin on 2017/8/17.
 */
public class Hello implements IHello{
    @Override
    public void sayHello(String str) {
        System.out.println("--sayHello::"+str);
    }
}
