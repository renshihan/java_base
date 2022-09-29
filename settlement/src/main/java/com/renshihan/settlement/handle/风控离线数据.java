package com.renshihan.settlement.handle;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.renshihan.settlement.bean.BrokerTransferExcel;
import com.renshihan.settlement.bean.MarketNftInfoExcel;
import com.renshihan.settlement.bean.SavingOrderRecordPo;
import com.renshihan.settlement.utils.DateUtil;
import com.renshihan.settlement.utils.HPoiUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**


 SELECT  mc.id, mn.token_id, mc.future_address, mc.network
 FROM
 tb_market_nft mn,tb_market_commodity mc
 WHERE mn.commodity_id = mc.id
 and mc.future_address IN (
 "0x957bD1556C4FB00c62d6d392c6d8d4dF82FB6e4e",
 "0x1038bFf48370574501044292993C0A93CfC91Cd3",
 "0x2e4066743d917Ead1EE6A2bC495B330048CE363f",
 "0x137352b26bfc6d17230df8885d675dd64c801b37",
 "0xa338cA2a79d2d373Dc402bAFF94E0c45Bd41B915",
 "0x73A2fef690CEB60dF69428b45154659Aa48dE71C",
 "0xa80617371a5f511bf4c1ddf822e6040acaa63e71",
 "0x11716db9B822F0ac5FdC978AB7cCE0328dB83614"
 )

 order by mn.id limit 1000;
 */
@Slf4j
public class 风控离线数据 {
    public static String inserSql="INSERT INTO `<table_name>` (`token_id`, `future_address`, `network`) VALUES ('%s', '%s', '%s');";

    //    private static String filePath = "/Users/renshihan/Desktop/";
    private static String filePath = "/Users/bj00078ml/Downloads/";
    private static String fileClassPath = "/Users/bj00078ml/other/java_base/settlement/sql/";
    private static List<String> bibizhuanguiji = new ArrayList<>();
    private static List<String> chongfuchukuan = new ArrayList<>();
    private static List<String> lixidaobibi = new ArrayList<>();
    private static List<String> lixidaozhichu = new ArrayList<>();
    private static Map<String, List<SavingOrderRecordPo>> bibiSaving = new HashMap<>();
    private static Map<String, BigDecimal> diudanMap = new HashMap<>();
    private static Map<String, BigDecimal> lixiMap = new HashMap<>();


    public static void main(String[] args) {
//        List<MarketNftInfoExcel> infoExcels = getByBrokerFileName(
//                "离线资产0_1.csv",
//                "离线资产1_1000.csv",
//                "离线资产1001_2000.csv",
//                "离线资产2001_3000.csv",
//                "离线资产3001_4000.csv",
//                "离线资产4001_5000.csv",
//                "离线资产5001_6000.csv",
//                "离线资产6001_7000.csv",
//                "离线资产7001_8000.csv",
//                "离线资产8001_9000.csv",
//                "离线资产9001_10000.csv",
//                "离线资产13181.csv",
//                "离线资产15323.csv"
//        );
//        List<MarketNftInfoExcel> infoExcels = getByBrokerFileName(
//                "query_result.csv"
//        );

//        List<MarketNftInfoExcel> infoExcels = getByBrokerFileName(
//                "test_contract_2.csv"
//        );


        List<MarketNftInfoExcel> infoExcels = getByBrokerFileName(
                "solana0_1000.csv",
                "solana1000_2000.csv",
                "solana2000_3000.csv",
                "solana3000_4000.csv",
                "solana4000_5000.csv",
                "solana5000_6000.csv",
                "solana6000_7000.csv",
                "solana7000_8000.csv",
                "solana8000_9000.csv"
        );

        infoExcels.sort(Comparator.comparing(MarketNftInfoExcel::getId).reversed());

        List<String> total = infoExcels.stream().map(风控离线数据::print).collect(Collectors.toList());
        Map<String, List<MarketNftInfoExcel>> contractMap = infoExcels.stream().collect(Collectors.groupingBy(MarketNftInfoExcel::getFuture_address));
        System.out.println("合约总数："+contractMap.keySet().size());
//        contractMap.entrySet().forEach(entry->System.out.println(String.format("%s 资产数：%s",entry.getKey(),entry.getValue().size())));
        System.out.println("离线资产总数：" + total.size());
        long totalCount=total.size();

        long disCount = total.stream().distinct().count();

        if(totalCount!=disCount){
            System.out.println("检查到有重复insert数据，请排查!!!!");
        }else {
            Optional<Long> min = infoExcels.stream()
                    .map(MarketNftInfoExcel::getId)
                    .min(Long::compare);

            Optional<Long> max = infoExcels.stream()
                    .map(MarketNftInfoExcel::getId)
                    .max(Long::compare);
            String time = DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss");
//            total.forEach(System.out::println);
            String infoName = String.format("asset_%s_%s_total_%s_%s.sql",min.orElse(-1L),max.orElse(-1L),infoExcels.size(),time);
            try {
                FileUtils.writeLines(new File(fileClassPath + "/"+infoName),total);
            } catch (IOException e) {
                log.info("写入异常{}",infoName,e);
            }
        }
    }

    public static String print(MarketNftInfoExcel excel){
        String token_id = "";
        String network = "";
        String contract = "";
        if(excel.getNetwork().contains("SOLANA")){
            token_id = "sol-nft";
            network ="SOL";
            contract = excel.getFuture_address();
        }else {
            token_id = excel.getToken_id();
            network = excel.getNetwork().toUpperCase();
            contract = excel.getFuture_address().toLowerCase();
        }

        return String.format(inserSql,replace(token_id) ,replace(contract),replace(network));
    }

    private static List<MarketNftInfoExcel> getByBrokerFileName(String... brokerFileNames) {
        List<MarketNftInfoExcel> total=new ArrayList<>();
        for (String fileName : brokerFileNames) {
            List<MarketNftInfoExcel> excels = buildBrokerList(fileName);
            Optional<Long> min = excels.stream()
                    .map(MarketNftInfoExcel::getId)
                    .min(Long::compare);

            Optional<Long> max = excels.stream()
                    .map(MarketNftInfoExcel::getId)
                    .max(Long::compare);
            log.info("{} {}~{} count={}",fileName,min.orElse(-1L),max.orElse(-1L),excels.size());
            total.addAll(excels);
        }

        return total;
    }

    private static List<MarketNftInfoExcel> buildBrokerList(String fileName) {

        if(fileName.contains(".csv")){
            return HPoiUtils.exeCsv(new File(filePath + fileName), MarketNftInfoExcel.getParams(), MarketNftInfoExcel.class);
        }

        return new ArrayList<>();
    }
    public static String replace(String str) {

        String newString = str.replaceAll("\\\"", "");//不想保留原来的字符串可以直接写成 “str =
        return newString;
        // 输出: 上海
    }
}
