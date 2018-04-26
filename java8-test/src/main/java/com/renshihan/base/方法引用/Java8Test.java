package com.renshihan.base.方法引用;

import java.util.ArrayList;
import java.util.List;

/**
 * @author renshihan@winchannel.net
 * @date 2018/4/26 13:35
 */
public class Java8Test {
    public static void main(String[] args) {
        List names=new ArrayList();
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);

    }
}
