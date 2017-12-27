package com.renshihan.thread.immutable;

/**
 * Created by admin on 2017/12/27.
 * 创建一个person类，创建三个线程访问该类
 */
public class Main {
    public static void main(String[] args) {
        //实例创建后，状态不再发生改变 是必要条件
        Person person=new Person("Alice","陕西");
        new PrintPersonThread(person).start();
        new PrintPersonThread(person).start();
        new PrintPersonThread(person).start();
    }
}
