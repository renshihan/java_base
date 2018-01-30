package com.renshihan.javabase.time;

import java.text.SimpleDateFormat;

/**
 * Created by admin on 2017/8/21.
 */
public class Test1 {
    private static SimpleDateFormat yyyyMMddF=new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat yyyyF=new SimpleDateFormat("yyyy");
    //获取当前时间在一年中是第几天

    public static void main(String[] args) throws Exception{
//        System.out.println("----"+getDayByTime(new Date()));
        String fo="0101";
        String time="20170201";
        fo=yyyyF.format(yyyyMMddF.parse(time))+fo;
        Long a=yyyyMMddF.parse(time).getTime()-yyyyMMddF.parse(fo).getTime();
        Integer day=Integer.parseInt(a/(1000 * 60 * 60 *24)+"")+1;
        System.out.println("---day---"+day);
    }
}
