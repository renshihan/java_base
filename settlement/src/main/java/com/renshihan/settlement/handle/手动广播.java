package com.renshihan.settlement.handle;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import com.dtflys.forest.Forest;
import com.dtflys.forest.annotation.ForestClient;
import com.google.common.collect.Lists;
import com.renshihan.settlement.utils.BabylongClient;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class 手动广播 {
//    public static final String url="https://mainnet-rpc-bl.bitlayer.org";
    public static final String url="https://rpc.bitlayer.org";
    public static void main(String[] args) {
        BabylongClient client= Forest.client(BabylongClient.class);
        List<String> strings = FileUtil.readLines(new File("/Users/renshihan/Documents/GitHub1/java_base/settlement/src/main/resources/abc"), "utf-8");
        for (String rawData : strings) {

            JSONObject jsonObject = buildJsonRpcParams("", rawData.trim());


            String call = client.call(url,jsonObject);

            System.out.println(call);
        }
    }


    protected static JSONObject buildJsonRpcParams(String methodName, Object ... args) {
        JSONObject result = new JSONObject();
        result.put("jsonrpc", "2.0");
        result.put("method", "eth_sendRawTransaction");
        result.put("id", "1");
        List<Object> paramsList = Lists.newArrayList();
        paramsList.addAll(Arrays.asList(args));
        result.put("params", paramsList);
        return result;
    }
}
