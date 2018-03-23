package com.renshihan.commons.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangzw on 2015/12/29.
 */
public class SafeUtil {
    private static Map<String, String> maps;
    static {
        init();
    }
    public static String clear(String value){
        for (String key :maps.keySet()) {
            if(value.contains(key))
                value = value.replace(key, maps.get(key));
        }
        return value;
    }

    private static void init(){
        maps = new HashMap<String,String>();
        maps.put("<", "&lt;");
        maps.put(">", "&gt;");
        maps.put("\"", "&quot;");
        maps.put("\'", "&apos;");
    }
}
