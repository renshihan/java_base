package com.renshihan.thread.immutable;

/**
 * Created by admin on 2017/12/27.
 * 用于持续显示构造函数中传入的person类的实例
 */
public class PrintPersonThread extends Thread{
    private Person person;

    public PrintPersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+"prints"+person);
        }
    }
}
