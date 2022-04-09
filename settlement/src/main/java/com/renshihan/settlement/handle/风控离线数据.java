package com.renshihan.settlement.handle;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.renshihan.settlement.bean.BrokerTransferExcel;
import com.renshihan.settlement.bean.MarketNftInfoExcel;
import com.renshihan.settlement.bean.SavingOrderRecordPo;
import com.renshihan.settlement.utils.HPoiUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class 风控离线数据 {

    //    private static String filePath = "/Users/renshihan/Desktop/";
    private static String filePath = "/Users/bj00078ml/Downloads/";
    private static List<String> bibizhuanguiji = new ArrayList<>();
    private static List<String> chongfuchukuan = new ArrayList<>();
    private static List<String> lixidaobibi = new ArrayList<>();
    private static List<String> lixidaozhichu = new ArrayList<>();
    private static Map<String, List<SavingOrderRecordPo>> bibiSaving = new HashMap<>();
    private static Map<String, BigDecimal> diudanMap = new HashMap<>();
    private static Map<String, BigDecimal> lixiMap = new HashMap<>();


    public static void main(String[] args) {
        List<MarketNftInfoExcel> infoExcels = getByBrokerFileName("export_result.csv");

        for (MarketNftInfoExcel infoExcel : infoExcels) {
            System.out.println(JSONObject.toJSONString(infoExcel));
        }


    }



    private static List<MarketNftInfoExcel> getByBrokerFileName(String... brokerFileNames) {
        List<MarketNftInfoExcel> brokerTransferExcels = new ArrayList<>();
        for (String brokerFileName : brokerFileNames) {
            System.out.println("开始倒入----fileName=" + brokerFileName);
            List<MarketNftInfoExcel> marketNftInfoExcels = buildBrokerList(brokerFileName);
            brokerTransferExcels.addAll(marketNftInfoExcels);
            System.out.println("导入完成----fileName=" + brokerFileName + "，导入总数-->"+brokerTransferExcels.size());
        }

        return brokerTransferExcels;
    }

    private static List<MarketNftInfoExcel> buildBrokerList(String fileName) {

        if(fileName.contains(".csv")){
            return HPoiUtils.exeCsv(new File(filePath + fileName), MarketNftInfoExcel.getParams(), MarketNftInfoExcel.class);
        }

        return null;
    }
}
