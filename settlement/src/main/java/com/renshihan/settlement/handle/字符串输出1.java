package com.renshihan.settlement.handle;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.renshihan.commons.util.JsonUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class 字符串输出1 {
    public static void main(String[] args)throws Exception {
//        String jsonstr="{\\\"image\\\":\\\"https://krkstorage2022.blob.core.windows.net/nftcharacter2022/2_737_1.png\\\",\\\"name\\\":\\\"Spear Fang\\\",\\\"attributes\\\":[{\\\"trait_type\\\":\\\"Range\\\",\\\"value\\\":25},{\\\"trait_type\\\":\\\"Attack Speed\\\",\\\"value\\\":1.41},{\\\"trait_type\\\":\\\"Health Points\\\",\\\"value\\\":43},{\\\"trait_type\\\":\\\"Damage\\\",\\\"value\\\":53},{\\\"trait_type\\\":\\\"AOE Damage\\\",\\\"value\\\":53},{\\\"trait_type\\\":\\\"Deploy Time\\\",\\\"value\\\":0.6},{\\\"trait_type\\\":\\\"Movement Speed\\\",\\\"value\\\":105},{\\\"trait_type\\\":\\\"Energy\\\",\\\"value\\\":2},{\\\"trait_type\\\":\\\"Count\\\",\\\"value\\\":3}]}\",\"timestamp\":\"2022-08-29T12:00:07.099096590Z\"}";
//        jsonstr="{\\\"image\\\":\\\"https://krkstorage2022.blob.core.windows.net/nftcharacter2022/2_737_1.png\\\",\\\"name\\\":\\\"Spear Fang\\\",\\\"attributes\\\":[{\\\"trait_type\\\":\\\"Range\\\",\\\"value\\\":25},{\\\"trait_type\\\":\\\"Attack Speed\\\",\\\"value\\\":1.41},{\\\"trait_type\\\":\\\"Health Points\\\",\\\"value\\\":43},{\\\"trait_type\\\":\\\"Damage\\\",\\\"value\\\":53},{\\\"trait_type\\\":\\\"AOE Damage\\\",\\\"value\\\":53},{\\\"trait_type\\\":\\\"Deploy Time\\\",\\\"value\\\":0.6},{\\\"trait_type\\\":\\\"Movement Speed\\\",\\\"value\\\":105},{\\\"trait_type\\\":\\\"Energy\\\",\\\"value\\\":2},{\\\"trait_type\\\":\\\"Count\\\",\\\"value\\\":3}]}";
//        jsonstr=jsonstr.replaceAll("\\\\","");
////        System.out.println(jsonstr);
//        System.out.println(JSONObject.toJSONString(JSONObject.parseObject(jsonstr)));
//
        List<Map<String, Object>> nftInfos = getNftInfos();
        System.out.println("-----nftInfos--->"+nftInfos.size());
        List<Map<String, Object>> commoditys = getCommoditys();
        System.out.println("-----commoditys--->"+commoditys.size());
        List<String> allContracts = commoditys.stream().map(m -> m.get("future_address") + "").collect(Collectors.toList());

        System.out.println("-----hasContrats--->"+allContracts.stream().distinct().count());

        Map<String, List<String>> collect = allContracts.stream().collect(Collectors.groupingBy(s -> s));
        List<String> contractss=new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : collect.entrySet()) {
            if(entry.getValue().size()>1){
                contractss.add(entry.getKey());
            }
        }

        String collect1 = contractss.stream().map(s -> "'" + s + "'").collect(Collectors.joining(","));
        System.out.println(collect1);

//        List<Map<String, Object>> filterList = nftInfos.stream().filter(com -> !allContracts.contains(com.get("future_address") + "")).collect(Collectors.toList());
//
//        System.out.println(JsonUtil.toJson(filterList));

    }
    public static  List<Map<String,Object>> getCommoditys()throws Exception{
        List<String> string1 = FileUtils.readLines(new File("/Users/bj00078ml/other/java_base/settlement/src/main/resources/export_result (8).json"), "UTF-8");
        String json1=string1.get(0);


        List<Map<String,Object>> list1 = (List)JSONPath.read(json1, "$.data");


        List<String> string2 = FileUtils.readLines(new File("/Users/bj00078ml/other/java_base/settlement/src/main/resources/export_result (9).json"), "UTF-8");
        String json2=string2.get(0);


        List<Map<String,Object>> list2 = (List)JSONPath.read(json2, "$.data");


        list1.addAll(list2);
        System.out.println("");
        return list1;
    }
    public static  List<Map<String,Object>> getNftInfos()throws Exception{
        List<String> string1 = FileUtils.readLines(new File("/Users/bj00078ml/other/java_base/settlement/src/main/resources/export_result (6).json"), "UTF-8");
        String json1=string1.get(0);


        List<Map<String,Object>> list1 = (List)JSONPath.read(json1, "$.data");


        List<String> string2 = FileUtils.readLines(new File("/Users/bj00078ml/other/java_base/settlement/src/main/resources/export_result (7).json"), "UTF-8");
        String json2=string2.get(0);


        List<Map<String,Object>> list2 = (List)JSONPath.read(json2, "$.data");


        list1.addAll(list2);

        return list1;
    }
}
