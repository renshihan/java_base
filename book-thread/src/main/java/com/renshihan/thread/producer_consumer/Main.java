package com.renshihan.thread.producer_consumer;

/**
 * Created by admin on 2017/12/30.
 * 模拟场景：
 * 有3位糕点师制作蛋糕并放在桌子上，然后有3位客人来吃这些糕点
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
