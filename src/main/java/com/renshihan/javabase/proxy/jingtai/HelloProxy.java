package com.renshihan.javabase.proxy.jingtai;

/**
 * Created by admin on 2017/8/17.
 */
public class HelloProxy implements IHello{
    private IHello target;
    @Override
    public void sayHello(String str) {
        Logger.start();
        target.sayHello(str);
        Logger.end();
    }
    public HelloProxy(IHello target){
        this.target=target;
    }

}
