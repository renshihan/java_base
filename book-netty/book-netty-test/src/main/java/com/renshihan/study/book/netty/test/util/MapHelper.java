package com.renshihan.study.book.netty.test.util;

import org.apache.commons.collections.MapUtils;

import java.util.*;

public class MapHelper extends MapUtils{
    public static List<Map.Entry<String,String>> assicCompare(Map<String,String> paraMap){
        List<Map.Entry<String,String>> infoIds=new ArrayList<>(paraMap.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> stringStringEntry, Map.Entry<String, String> t1) {
                return (stringStringEntry.getKey()).toString().compareTo(t1.getKey());
            }
        });
        return infoIds;
    }
    public static Map<String,String> strToMap(String mapStr){
        if(StringHelper.isBlank(mapStr)){
            return new HashMap<>();
        }
        mapStr=mapStr.substring(1,mapStr.length()-1);
        mapStr=mapStr.replaceAll("\n","");
        Map<String,String> reqMap=new HashMap<>();
        String[] kvs=mapStr.split(",");
        for(String kv:kvs){
            String[] kav=kv.split("=");
            String key=kav[0].trim();
            if(kav.length==2){
                String value=kav[1];
                if("null".equals(value)){
                    value="";
                }
                reqMap.put(key,value);
            }else{
                reqMap.put(key,"");
            }
        }
        return reqMap;
    }
    public static void main(String[] args) {

    }
}
