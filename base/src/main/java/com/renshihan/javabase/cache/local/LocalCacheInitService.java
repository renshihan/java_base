package com.renshihan.javabase.cache.local;

/**
 * Created by admin on 2017/6/19.
 */
public class LocalCacheInitService{
    public static void main(String[] args) {
        int a =1;
        try {
            if(1==a){
                System.out.println("----111");
                return;
            }
            System.out.println("--------2222");
        }finally {
            System.out.println("233333");
        }
    }
}
