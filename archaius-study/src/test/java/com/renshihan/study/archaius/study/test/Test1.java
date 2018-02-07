package com.renshihan.study.archaius.study.test;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

public class Test1 {
    public static void main(String[] args) {
        DynamicStringProperty a= DynamicPropertyFactory.getInstance().getStringProperty("channel.test1","tes11111");
        System.out.println("----"+a.get());
    }
}
