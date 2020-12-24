package com.renshihan.base.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//常见的list用法
public class BaseLists {
    public static void main(String[] args) {
        //set转list
        Map<Long,String> map=new HashMap<>(0);
        List<Long> list=new ArrayList<>(map.keySet());
    }
}
