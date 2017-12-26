package com.renshihan.javabase.proxy.jdk;

import java.util.Date;

/**
 * Created by admin on 2017/8/17.
 */
public class DynaLogger implements ILogger{
    @Override
    public void start() {
        System.out.println(new Date() +":: start");
    }
    @Override
    public void end() {
        System.out.println(new Date() +":: end");
    }
}
