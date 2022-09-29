//package com.renshihan.settlement.handle;
//
//import com.alibaba.fastjson.JSONObject;
//import com.renshihan.settlement.bean.NftInfo;
//import org.apache.commons.io.FileUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class NftInfoToExcel {
//    public static void main(String[] args) throws IOException {
//        File nftInfosFile = new File("/Users/bj00078ml/other/java_base/settlement/src/main/resources/nftInfos.txt");
//        List<String> strings = FileUtils.readLines(nftInfos);
//
//        List<NftInfo> nftInfofs = strings.stream().map(str -> JSONObject.parseObject(str, NftInfo.class)).collect(Collectors.toList());
//    }
//}
