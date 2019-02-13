package com.renshihan.thread.chapter3.exmple1;

/**
 * Created by admin on 2017/12/30.
 * 模拟场景：
 * 有3位糕点师制作蛋糕并放在桌子上，然后有3位客人来吃这些糕点
 * 角色：DATA - 由生产者角色生成
 *      PRODUCER - 生成DATA，并传递给CHANNEL角色
 *      CONSUMER - 从CHANNEL角色中获取DATA角色并使用
 *      CHANNEL  - 保管从PRODUCER角色中获取的DATA角色，还会响应CONSUMER角色的请求，传递DATA角色。为了确保安全性，
 * CHANNEL角色会对PRODUCER角色和CONSUMER角色的访问执行互斥处理。
 *
 */
public class Main {
    public static void main(String[] args) {
        Table table=new Table(3);   //三个桌子
        new MakerThread("糕点师1",table,31415).start();
        new MakerThread("糕点师2",table,34522).start();
        new MakerThread("糕点师3",table,65673).start();
        new EaterThread("客人1",table,92344).start();
        new EaterThread("客人2",table,72344).start();
        new EaterThread("客人3",table,12356).start();

    }
}
