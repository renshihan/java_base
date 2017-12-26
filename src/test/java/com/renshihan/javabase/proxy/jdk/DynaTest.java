package com.renshihan.javabase.proxy.jdk;

import com.renshihan.javabase.proxy.jingtai.Hello;
import com.renshihan.javabase.proxy.jingtai.IHello;

/**
 * Created by admin on 2017/8/17.
 */
public class DynaTest {
    public static void main(String[] args) {
        IHello hello=new DynaProxyHello().bind(new Hello(),new DynaLogger());
        hello.sayHello("1234");
    }
}
