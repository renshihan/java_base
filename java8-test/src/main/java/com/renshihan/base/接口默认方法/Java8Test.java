package com.renshihan.base.接口默认方法;

/**
 * @author renshihan@winchannel.net
 * @date 2018/4/26 16:09
 */
public class Java8Test {
    public static void main(String[] args) {
        Vechive vechive=new Car();
        vechive.print();
    }
}
interface Vechive{
    default void print(){
        System.out.println("一辆车");
    }
    static void anlaba(){
        System.out.println("按喇叭!!");
    }
}

interface FourWheeler {
    default void print(){
        System.out.println("我是一辆四轮车!");
    }
}

class Car implements Vechive,FourWheeler{
    @Override
    public void print() {
        Vechive.super.print();
        FourWheeler.super.print();
        Vechive.anlaba();
        System.out.println("-----------");
    }
}
