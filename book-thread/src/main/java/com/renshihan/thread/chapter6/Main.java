package com.renshihan.thread.chapter6;

/**
 * Created by admin on 2018/11/22.
 */
public class Main {
    public static void main(String[] args) {
        Data data=new Data(10);
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new WriterThread(data,"abc").start();

    }
}
