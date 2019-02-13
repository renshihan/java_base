package com.renshihan.thread.chapter3.exmple2;

import java.util.concurrent.Exchanger;

/**
 * Created by admin on 2018/1/3.
 * 1.java.util.concurrent.Exchanger类：
 *      用于让两个线程安全的交换对象
 */
public class Main {
    public static void main(String[] args) {
        Exchanger<char[]> exchanger=new Exchanger<>();
        char[] buffer1=new char[10];
        char[] buffer2=new char[10];
        new ProducerThread(exchanger,buffer1,123123).start();
        new ConsumerThread(exchanger,buffer2,232323).start();
    }
}
