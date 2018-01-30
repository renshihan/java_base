package com.renshihan.javabase.proxy.jingtai;

import java.util.Date;

/**
 * Created by admin on 2017/8/17.
 */
public class Logger{
    public static void start() {
        System.out.println(new Date() +":: start");
    }

    public static void end() {
        System.out.println(new Date() +":: end");
    }
}
