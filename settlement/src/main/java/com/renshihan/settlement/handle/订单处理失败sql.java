package com.renshihan.settlement.handle;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class 订单处理失败sql {
    public static void main(String[] args)throws Exception {
        long nowTime = System.currentTimeMillis();
        String json_str= FileUtil.readString("/Users/renshihan/ideaProject/java_base/settlement/src/main/resources/export_result.json","UTF-8");
        JSON parse = JSONUtil.parse(json_str);

        System.out.println(parse.toStringPretty());
    }
}
