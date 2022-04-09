package com.renshihan.settlement.handle;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.gjing.tools.common.util.ParamUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.renshihan.settlement.bean.BrokerTransferExcel;
import com.renshihan.settlement.bean.CurrencySourceTypeTotalAmountBean;
import com.renshihan.settlement.bean.SavingOrderRecordPo;
import com.renshihan.settlement.enums.OrderEnum;
import com.renshihan.settlement.utils.AmountUtil;
import com.renshihan.settlement.utils.DateUtil;
import com.renshihan.settlement.utils.HPoiUtils;
import com.renshihan.settlement.utils.LogUtils;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * SELECT f_currency, f_source_type, f_side, f_product_type, sum( f_amount ) as f_amount
 * FROM
 * t_saving_order_record
 * GROUP BY
 * f_currency,
 * f_source_type,
 * f_side,
 * f_product_type
 * ORDER BY f_currency;
 * <p>
 * 挖矿宝内部账户反推出此时的系统账户的资金情况。
 */
@Slf4j
public class 全量对账 {
    //    private static String filePath = "/Users/renshihan/Desktop/";
    private static String filePath = "/Users/renshihan/Desktop/1月份挖矿宝与清结算数据/";
    private static List<String> bibizhuanguiji = new ArrayList<>();
    private static List<String> chongfuchukuan = new ArrayList<>();
    private static List<String> lixidaobibi = new ArrayList<>();
    private static List<String> lixidaozhichu = new ArrayList<>();
    private static Map<String, List<SavingOrderRecordPo>> bibiSaving = new HashMap<>();
    private static Map<String, BigDecimal> diudanMap = new HashMap<>();
    private static Map<String, BigDecimal> lixiMap = new HashMap<>();

    public static void main(String[] args) {

//        List<BrokerTransferExcel> brokers = getByBrokerFileName(
//                "0119-broker-transfer-action.xlsx",
//                "0121-broker-transfer-action.xlsx",
//                "0122-broker-transfer-action.xlsx",
//                "0123-broker-transfer-action.xlsx",
//                "0124-broker-transfer-action.xlsx",
//                "0125-broker-transfer-action.xlsx",
//                "0126-broker-transfer-action.xlsx",
//                "0127-broker-transfer-action.xlsx",
//                "0128-broker-transfer-action.xlsx",
//                "0202-broker-transfer-action.xlsx",
//                "0203-broker-transfer-action.xlsx",
//                "0210-01-broker-transfer-action.xlsx", "0210-02-broker-transfer-action.xlsx",
//                "0212-01-broker-transfer-action.xlsx", "0212-02-broker-transfer-action.xlsx",
//                "0222-01-broker-transfer-action.xlsx", "0222-02-broker-transfer-action.xlsx", "0222-03-broker-transfer-action.xlsx",
//                "0223-01-broker-transfer-action.xlsx", "0223-02-broker-transfer-action.xlsx", "0223-03-broker-transfer-action.xlsx"
//        );
//
//
//
//
//        List<SavingOrderRecordPo> recordPos =getByWkbFileName(
//                "0119-saving-order-record.xlsx",
//                "0121-saving-order-record.xlsx",
//                "0122-saving-order-record.xlsx",
//                "0123-saving-order-record.xlsx",
//                "0124-saving-order-record.xlsx",
//                "0125-saving-order-record.xlsx",
//                "0126-saving-order-record.xlsx",
//                "0127-saving-order-record.xlsx",
//                "0128-saving-order-record.xlsx",
//                "0202-saving-order-record.xlsx",
//                "0203-saving-order-record.xlsx",
//                "0210-01-saving-order-record.xlsx", "0210-02-saving-order-record.xlsx",
//                "0212-01-saving-order-record.xlsx",
//                "0222-01-saving-order-record.xlsx", "0222-02-saving-order-record.xlsx",
//                "0223-01-saving-order-record.xlsx", "0223-02-saving-order-record.xlsx"
//
//        );


//        List<BrokerTransferExcel> brokers = getByBrokerFileName(
//                "0216之前利息到支出清结算数据01.xlsx",
//                "0216之前利息到支出清结算数据02.xlsx",
//                "0216之前利息到支出清结算数据03.xlsx",
//                "0216之前利息到支出清结算数据04.xlsx",
//                "0216之前利息到支出清结算数据05.xlsx",
//                "0216之前利息到支出清结算数据06.xlsx",
//                "0216之前利息到币币清结算数据.xlsx"
//        );
//
//        List<SavingOrderRecordPo> recordPos =getByWkbFileName(
//                "0216之前收益复投订单流水数据01.xlsx",
//                "0216之前收益复投订单流水数据02.xlsx",
//                "0216之前收益复投订单流水数据03.xlsx",
//                "0216之前收益复投订单流水数据04.xlsx",
//                "0216之前收益复投订单流水数据05.xlsx",
//                "0216之前收益复投订单流水数据06.xlsx",
//                "0216之前利息到币币订单流水数据.xlsx"
//
//        );

//
//        List<BrokerTransferExcel> brokers = getByBrokerFileName(
//                "3017清结算数据01.xlsx",
//                "3017清结算数据02.xlsx",
//                "3017清结算数据03.xlsx"
//        );
//
//        List<SavingOrderRecordPo> recordPos =getByWkbFileName(
//                "3017订单流水数据01.xlsx",
//                "3017订单流水数据02.xlsx",
//                "3017订单流水数据03.xlsx"
//
//        );



        List<BrokerTransferExcel> brokers = getByBrokerFileName(
                "transfer_action 2.csv"
        );

        List<SavingOrderRecordPo> recordPos =getByWkbFileName(
                "order_record.csv"
        );
        proccess(brokers, recordPos);


        LogUtils.writeByFileWrite("=============综合分析=============");

        bibizhuanguiji.forEach(msg -> {
            //币币转归集
            LogUtils.writeByFileWrite("" + msg);
        });
        LogUtils.writeByFileWrite("   ");
        diudanMap.keySet().stream().forEach(key -> {
            LogUtils.writeByFileWrite("币币转归集丢单：" + key + " " + AmountUtil.rvZeroAndDot(diudanMap.get(key)));
        });
        LogUtils.writeByFileWrite("  ");
//        lixichazhi.keySet().stream().forEach(currency -> {
//            BigDecimal amount = lixichazhi.get(currency);
//            if (null != amount && BigDecimal.ZERO.compareTo(amount) != 0) {
//                LogUtils.writeByFileWrite("利息账户少划转：" + currency + " 利息账户需要划转到支出总金额 " + AmountUtil.rvZeroAndDot(amount));
//            }
//        });


        lixidaozhichu.stream().sorted().forEach(msg -> {
            LogUtils.writeByFileWrite("" + msg);
        });
        LogUtils.writeByFileWrite("  ");

        lixidaobibi.stream().sorted().forEach(msg -> {
            LogUtils.writeByFileWrite("" + msg);
        });
        LogUtils.writeByFileWrite("  ");
        lixiMap.keySet().stream().sorted().forEach(key->{
            LogUtils.writeByFileWrite(key + ":"+AmountUtil.rvZeroAndDot(lixiMap.get(key)));
        });
        LogUtils.writeByFileWrite("  ");
        chongfuchukuan.stream().sorted().forEach(msg -> {
            LogUtils.writeByFileWrite("" + msg);
        });
        System.out.println("------执行完成------");
    }

    private static List<BrokerTransferExcel> getByBrokerFileName(String... brokerFileNames) {
        List<BrokerTransferExcel> brokerTransferExcels = new ArrayList<>();
        for (String brokerFileName : brokerFileNames) {
            System.out.println("开始倒入----fileName=" + brokerFileName);
            List<BrokerTransferExcel> transferExcels = buildBrokerList(brokerFileName);
            brokerTransferExcels.addAll(transferExcels);
            System.out.println("导入完成----fileName=" + brokerFileName + "，导入总数-->"+transferExcels.size());
        }

        return brokerTransferExcels.stream()
                .filter(
                        //归集到支出不考虑
                        excel -> excel.getType() != 466
                ).sorted(Comparator.comparing(BrokerTransferExcel::getCreatedAt))
                .collect(Collectors.toList());
    }

    private static List<SavingOrderRecordPo> getByWkbFileName(String... wkbFileNames) {
        List<SavingOrderRecordPo> wkbOrderRecords = new ArrayList<>();
        for (String wkbFileName : wkbFileNames) {
            System.out.println("开始倒入----fileName=" + wkbFileName);
            List<SavingOrderRecordPo> recordPos = buildOrderRecords(wkbFileName);
            wkbOrderRecords.addAll(recordPos);
            System.out.println("导入完成----fileName=" + wkbFileName + "，导入总数-->"+recordPos.size());
        }
        return wkbOrderRecords.stream()
                .filter(
                        //归集到支出不考虑
                        excel -> excel.getSourceType() != OrderEnum.OrderSourceTypeEnum.FIXED_INCOME_ROLL_IN.getValue()
                ).sorted(Comparator.comparing(SavingOrderRecordPo::getCreateTime))
                .collect(Collectors.toList());
    }


    private static void proccess(List<BrokerTransferExcel> brokerTransferExcels, List<SavingOrderRecordPo> wkbOrderRecords) {

        LogUtils.writeByFileWrite(String.format("待匹配broker总数量=%s 挖矿宝流水总数据量=%s...", brokerTransferExcels.size(), wkbOrderRecords.size()));

        Map<String, List<BrokerTransferExcel>> bCreateTimeMap = brokerTransferExcels.stream().collect(Collectors.groupingBy(po -> DateUtil.format(new Date(po.getCreatedAt()), DateUtil.FORMAT_DAY)));
        //按照时间分
        Map<String, List<SavingOrderRecordPo>> wCreateTimeMap = wkbOrderRecords.stream().collect(Collectors.groupingBy(po -> DateUtil.format(new Date(po.getCreateTime()), DateUtil.FORMAT_DAY)));
        //
        bCreateTimeMap.keySet().stream().forEach(createTime -> {
            LogUtils.writeByFileWrite(String.format("开始处理---createTime=%s", createTime));
            List<BrokerTransferExcel> transferExcels = bCreateTimeMap.get(createTime);

            List<SavingOrderRecordPo> recordPos = wCreateTimeMap.get(createTime);

            Map<Integer, List<BrokerTransferExcel>> typeBrokerMap = transferExcels.stream().collect(Collectors.groupingBy(BrokerTransferExcel::getType));

            Map<Integer, List<SavingOrderRecordPo>> sourceWkbMap = recordPos.stream().collect(Collectors.groupingBy(SavingOrderRecordPo::getSourceType));

            typeBrokerMap.keySet().stream().sorted().forEach(type -> {
                String typeMsg = switchTransferType(type + "");
                List<Integer> sourceTyps = OrderEnum.OrderSourceTypeEnum.getByBrokerType(type);
                List<BrokerTransferExcel> brokerLists = typeBrokerMap.get(type);


                List<SavingOrderRecordPo> sourceRecords = sourceTyps.stream()
                        .map(
                                sourceType -> sourceWkbMap.containsKey(sourceType) ?
                                        sourceWkbMap.get(sourceType) :
                                        new ArrayList<SavingOrderRecordPo>()
                        )
                        .reduce((l1, l2) -> {
                            l1.addAll(l2);
                            return l1;
                        }).orElse(new ArrayList<>());

                if (brokerLists.size() != sourceRecords.size()) {
                    LogUtils.writeByFileWrite(String.format("%s %s，type=%s，数据不匹配 broker总量=%s,挖矿宝总量=%s", createTime, typeMsg, type, brokerLists.size(), sourceRecords.size()));
//                    LogUtils.writeByFileWrite();(String.format("%s broker=%s",createTime, JSONObject.toJSONString(CollectionUtil.sort(brokerLists,Comparator.comparing(BrokerTransferExcel::getAmount)))));
//                    LogUtils.writeByFileWrite();(String.format("%s 挖矿宝=%s",createTime, JSONObject.toJSONString(CollectionUtil.sort(sourceRecords,Comparator.comparing(SavingOrderRecordPo::getAmount)))));
                }
                if (typeMsg.equals("币币到归集")) {
                    type1Proccess(brokerLists, sourceRecords, createTime);
                }

                if (typeMsg.equals("利息到支出")) {
                    type6Proccess(brokerLists, sourceRecords, createTime);
                }
                if (typeMsg.equals("利息到币币")) {
                    type8Proccess(brokerLists, sourceRecords, createTime);
                }


                if (typeMsg.equals("支出到币币")) {
//                    LogUtils.writeByFileWrite("支出到币币-----broker list count=" + brokerLists.size() + ",source records count=" + sourceRecords.size());
                    type7Proccess(brokerLists, sourceRecords, createTime);
                }



            });


        });

        LogUtils.writeByFileWrite("   ");

    }

    //币币到归集
    private static void type1Proccess(List<BrokerTransferExcel> brokerLists, List<SavingOrderRecordPo> sourceRecords, String createTime) {
        brokerLists.forEach(broker -> {
            //币种大写
            broker.setCurrency(broker.getCurrency().trim().toUpperCase());
        });

        //按照用户id分组
        Map<Long, List<BrokerTransferExcel>> userIdBrokersMap = brokerLists.stream()
                .collect(Collectors.groupingBy(broker -> broker.getFromUserId()));

        Map<Long, List<SavingOrderRecordPo>> userIdRecordsMap = sourceRecords.stream()
                .collect(Collectors.groupingBy(SavingOrderRecordPo::getUserId));


        List<Long> userIdsBroker = new ArrayList<>(userIdBrokersMap.keySet());
        List<Long> userIdsRecord = new ArrayList<>(userIdRecordsMap.keySet());

        if (userIdsBroker.size() != userIdsRecord.size()) {
            LogUtils.writeByFileWrite(createTime + " 币币划转归集 broker记录和挖矿宝记录用户数量不一致----brokerCount=" + userIdsBroker.size() + ",recordCount=" + userIdsRecord.size());
            for (Long aLong : userIdsBroker) {
                if (!userIdsRecord.contains(aLong)) {
                    LogUtils.writeByFileWrite(createTime + " 币币划转归集 broker记录和挖矿宝记录用户数量不一致 用户id=" + aLong + " broker有记录，挖矿宝没有记录");
                }
            }
            for (Long aLong : userIdsRecord) {
                if (!userIdsBroker.contains(aLong)) {
                    LogUtils.writeByFileWrite(createTime + " 币币划转归集 broker记录和挖矿宝记录用户数量不一致 用户id=" + aLong + " 挖矿宝有记录，broker没有记录");
                }
            }

        }


        //遍历broker数据
        userIdBrokersMap.keySet().forEach(userId -> {
            //获取该用户该日期的所有broker数据
            List<BrokerTransferExcel> brokerTransferExcels = userIdBrokersMap.get(userId);

            List<SavingOrderRecordPo> orderRecordPos = userIdRecordsMap.containsKey(userId) ? userIdRecordsMap.get(userId) : new ArrayList<>();

            if (brokerTransferExcels.size() != orderRecordPos.size()) {
                LogUtils.writeByFileWrite(createTime + " 币币划转归集 同一用户id=" + userId + "，broker记录和挖矿宝记录数量不一致 brokerCount=" + brokerTransferExcels.size() + ",recordCount=" + orderRecordPos.size());
            }

            //按照币种分组
            Map<String, List<BrokerTransferExcel>> currencyBrokersMap = brokerTransferExcels.stream().collect(Collectors.groupingBy(BrokerTransferExcel::getCurrency));

            Map<String, List<SavingOrderRecordPo>> currencyRecordsMap = orderRecordPos.stream().collect(Collectors.groupingBy(SavingOrderRecordPo::getCurrency));

            //基于broker用户记录做判断
            currencyBrokersMap.keySet().stream().forEach(currency -> {
                List<BrokerTransferExcel> brokers = currencyBrokersMap.get(currency);
                List<SavingOrderRecordPo> records = currencyRecordsMap.get(currency);
                //1.没有该币种造成丢单
                if (!currencyRecordsMap.containsKey(currency)) {
                    //用户有该币种broker划转记录，挖矿宝没有记录
                    BigDecimal totalAmount = brokers.stream().map(BrokerTransferExcel::getAmount).reduce(BigDecimal::add).get();
                    bibizhuanguiji.add(createTime + " 币币到归集 broker有用户数据，挖矿宝没有用户数据，丢单: " +
                            "userId=" + userId + "," +
                            "currency=" + currency + "," +
                            "totalAmount=" + AmountUtil.rvZeroAndDot(totalAmount) + "," +
                            "count=" + brokers.size());
                    String diudanKey = createTime + "_" + userId + "_" + currency;
                    if (diudanMap.containsKey(diudanKey)) {
                        diudanMap.put(diudanKey, diudanMap.get(diudanKey).add(totalAmount));
                    } else {
                        diudanMap.put(diudanKey, totalAmount);
                    }
                    return;
                }

                //2.有该币种，需要匹配对应金额
                Map<String, Integer> countAmountsBrokerMap = brokers.stream().collect(Collectors.toMap(b -> AmountUtil.rvZeroAndDot(b.getAmount()), b -> 1, (a, b) -> a + b));
                Map<String, Integer> countAmountsRecordsMap = records.stream().collect(Collectors.toMap(b -> AmountUtil.rvZeroAndDot(b.getAmount()), b -> 1, (a, b) -> a + b));

                countAmountsBrokerMap.keySet().stream().forEach(amount -> {
                    Integer brokerCount = countAmountsBrokerMap.get(amount);
                    Integer recordCount = countAmountsRecordsMap.get(amount);

                    if (null == recordCount) {
                        bibizhuanguiji.add(createTime + " 币币到归集 broker有记录，但挖矿宝没有该币种金额下单记录，丢单: " +
                                "userId=" + userId + "," +
                                "currency=" + currency + "," +
                                "amount=" + amount);
                        return;
                    }

                    if (brokerCount.equals(recordCount)) {
                        return;
                    }
                    if (brokerCount > recordCount) {
                        bibizhuanguiji.add(createTime + " 币币到归集 broker有记录，但记录该金额的笔数比挖矿宝记录条数多，丢单: " +
                                "userId=" + userId + "," +
                                "currency=" + currency + "," +
                                "amount=" + amount + "," +
                                "brokerCount=" + brokerCount + "," +
                                "recordCount=" + recordCount
                        );
                        return;
                    }
                    if (brokerCount < recordCount) {
                        bibizhuanguiji.add(createTime + " 币币到归集 broker有记录，但记录该金额的笔数比挖矿宝记录条数少，原因未知: " +
                                "userId=" + userId + "," +
                                "currency=" + currency + "," +
                                "amount=" + amount + "," +
                                "brokerCount=" + brokerCount + "," +
                                "recordCount=" + recordCount
                        );
                        return;
                    }
                });

            });

            //基于订单流水分析
            currencyRecordsMap.keySet().stream().forEach(currency -> {
                List<BrokerTransferExcel> brokers = currencyBrokersMap.get(currency);
                List<SavingOrderRecordPo> records = currencyRecordsMap.get(currency);
                if (!currencyBrokersMap.containsKey(currency)) {
                    //用户有该币种broker划转记录，挖矿宝没有记录
                    BigDecimal totalAmount = records.stream().map(SavingOrderRecordPo::getAmount).reduce(BigDecimal::add).get();
                    bibizhuanguiji.add(createTime + " 币币到归集 订单流水有数据，broker没有数据，原因未知: " +
                            "userId=" + userId + "," +
                            "currency=" + currency + "," +
                            "totalAmount=" + AmountUtil.rvZeroAndDot(totalAmount) + "," +
                            "count=" + records.size());
                    return;
                }

                //2.有该币种，需要匹配对应金额
                Map<String, Integer> countAmountsBrokerMap = brokers.stream().collect(Collectors.toMap(b -> AmountUtil.rvZeroAndDot(b.getAmount()), b -> 1, (a, b) -> a + b));
                Map<String, Integer> countAmountsRecordsMap = records.stream().collect(Collectors.toMap(b -> AmountUtil.rvZeroAndDot(b.getAmount()), b -> 1, (a, b) -> a + b));

                countAmountsRecordsMap.keySet().stream().forEach(amount -> {
                    Integer recordCount = countAmountsRecordsMap.get(amount);
                    Integer brokerCount = countAmountsBrokerMap.get(amount);
                    if (null == brokerCount) {
                        bibizhuanguiji.add(createTime + " 币币到归集 订单流水有记录，但broker没有该币种金额下单记录，原因未知: " +
                                "userId=" + userId + "," +
                                "currency=" + currency + "," +
                                "amount=" + amount);
                        return;
                    }
                });

            });


        });

        //流水表
        userIdRecordsMap.keySet().forEach(userId -> {
            //流水表有数据，broker没数据
            if (!userIdBrokersMap.containsKey(userId)) {

                List<SavingOrderRecordPo> recordPos = userIdRecordsMap.get(userId);
                Map<String, BigDecimal> collect = recordPos.stream().collect(Collectors.toMap(po -> po.getCurrency(), SavingOrderRecordPo::getAmount, BigDecimal::add));
                collect.entrySet().forEach(entry->{
                    bibizhuanguiji.add(createTime + " 币币到归集 流水有数据，broker无数据:" + userId + " currency="+entry.getKey() + ",totalAmount="+AmountUtil.rvZeroAndDot(entry.getValue()));
                });

                return;
            }
        });


    }

    //利息到支出
    private static void type6Proccess(List<BrokerTransferExcel> brokerLists, List<SavingOrderRecordPo> sourceRecords, String createTime) {
        //只考虑总量
        Map<String, BigDecimal> currencyAmountBrokerMap = brokerLists.stream()
                .collect(Collectors.toMap(broker -> broker.getCurrency().toUpperCase(), broker -> broker.getAmount(), BigDecimal::add));

        Map<String, BigDecimal> currencyAmountWkbMap = sourceRecords.stream()
                .collect(Collectors.toMap(broker -> broker.getCurrency().toUpperCase(), record -> record.getAmount(), BigDecimal::add));

        currencyAmountBrokerMap.keySet().forEach(currency -> {

            String brokerCurrencyKey="broker记录_利息到支出_"+currency;
            String wkbZhichuCurrency="挖矿宝订单流水记录_利息到支出_"+currency;
            if(!lixiMap.containsKey(brokerCurrencyKey)){
                lixiMap.put(brokerCurrencyKey,BigDecimal.ZERO);
            }
            if(!lixiMap.containsKey(wkbZhichuCurrency)){
                lixiMap.put(wkbZhichuCurrency,BigDecimal.ZERO);
            }
            BigDecimal brokerTotalAmount = currencyAmountBrokerMap.get(currency);
            BigDecimal wkbTotalAmount = currencyAmountWkbMap.get(currency);

            lixiMap.put(brokerCurrencyKey,lixiMap.get(brokerCurrencyKey).add(brokerTotalAmount));
            lixiMap.put(wkbZhichuCurrency,lixiMap.get(wkbZhichuCurrency).add(wkbTotalAmount));

            if (null == wkbTotalAmount) {

                lixidaozhichu.add(String.format("%s 利息到支出 broker有记录，挖矿宝无记录,currency=%s,缺失总额=%s", createTime, currency, AmountUtil.rvZeroAndDot(brokerTotalAmount.toPlainString())));

                return;
            }
            if (brokerTotalAmount.compareTo(wkbTotalAmount) != 0) {
                lixidaozhichu.add(String.format("%s 利息到支出 broker和挖矿宝记录总金额有差异,currency=%s,broker总金额=%s,挖矿宝总金额=%s", createTime, currency, AmountUtil.rvZeroAndDot(brokerTotalAmount), AmountUtil.rvZeroAndDot(wkbTotalAmount)));
                return;
            }
        });

        currencyAmountWkbMap.keySet().forEach(currency -> {
            if (!currencyAmountBrokerMap.containsKey(currency)) {
                String wkbZhichuCurrency="挖矿宝订单流水记录_利息到支出_"+currency;
                BigDecimal wkbTotalAmount = currencyAmountWkbMap.get(currency);
                if(!lixiMap.containsKey(wkbZhichuCurrency)){
                    lixiMap.put(wkbZhichuCurrency,BigDecimal.ZERO);
                }
                lixiMap.put(wkbZhichuCurrency,lixiMap.get(wkbZhichuCurrency).add(wkbTotalAmount));
                lixidaozhichu.add(String.format("%s 利息到支出 broker无记录，挖矿宝有记录,currency=%s,缺失总额=%s", createTime, currency, AmountUtil.rvZeroAndDot(currencyAmountWkbMap.get(currency))));
                return;
            }
        });


    }

    //利息到币币
    private static void type8Proccess(List<BrokerTransferExcel> brokerLists, List<SavingOrderRecordPo> sourceRecords, String createTime) {
        //只考虑总量
        Map<String, BigDecimal> currencyAmountBrokerMap = brokerLists.stream()
                .collect(Collectors.toMap(broker -> broker.getCurrency().toUpperCase(), broker -> broker.getAmount(), BigDecimal::add));

        Map<String, BigDecimal> currencyAmountWkbMap = sourceRecords.stream()
                .collect(Collectors.toMap(broker -> broker.getCurrency().toUpperCase(), record -> record.getAmount(), BigDecimal::add));

        currencyAmountBrokerMap.keySet().forEach(currency -> {
            BigDecimal brokerTotalAmount = currencyAmountBrokerMap.get(currency);
            BigDecimal wkbTotalAmount = currencyAmountWkbMap.get(currency);
            String brokerCurrencyKey="broker记录_利息到币币_"+currency;
            String wkbZhichuCurrency="挖矿宝订单流水记录_利息到币币_"+currency;
            if(!lixiMap.containsKey(brokerCurrencyKey)){
                lixiMap.put(brokerCurrencyKey,BigDecimal.ZERO);
            }
            if(!lixiMap.containsKey(wkbZhichuCurrency)){
                lixiMap.put(wkbZhichuCurrency,BigDecimal.ZERO);
            }

            lixiMap.put(brokerCurrencyKey,lixiMap.get(brokerCurrencyKey).add(brokerTotalAmount));
            lixiMap.put(wkbZhichuCurrency,lixiMap.get(wkbZhichuCurrency).add(wkbTotalAmount));
            if (null == wkbTotalAmount) {
                lixidaobibi.add(String.format("%s 利息到币币 broker有记录，挖矿宝无记录,currency=%s,缺失总额=%s", createTime, currency, AmountUtil.rvZeroAndDot(brokerTotalAmount.toPlainString())));
                return;
            }

            if (brokerTotalAmount.compareTo(wkbTotalAmount) != 0) {
                lixidaobibi.add(
                        String.format("%s 利息到币币 broker和挖矿宝记录总金额有差异,currency=%s,broker总金额=%s,挖矿宝总金额=%s", createTime, currency, AmountUtil.rvZeroAndDot(brokerTotalAmount), AmountUtil.rvZeroAndDot(wkbTotalAmount))
                );
                return;
            }
        });

        currencyAmountWkbMap.keySet().forEach(currency -> {

            if (!currencyAmountBrokerMap.containsKey(currency)) {
                BigDecimal wkbTotalAmount = currencyAmountWkbMap.get(currency);
                String wkbZhichuCurrency="挖矿宝订单流水记录_利息到币币_"+currency;
                if(!lixiMap.containsKey(wkbZhichuCurrency)){
                    lixiMap.put(wkbZhichuCurrency,BigDecimal.ZERO);
                }
                lixiMap.put(wkbZhichuCurrency,lixiMap.get(wkbZhichuCurrency).add(wkbTotalAmount));
                lixidaobibi.add(
                        String.format("%s 利息到币币 broker无记录，挖矿宝有记录,currency=%s,缺失总额=%s", createTime, currency, AmountUtil.rvZeroAndDot(currencyAmountWkbMap.get(currency)))
                );
                return;
            }
        });


    }

    //支出划转币币
    private static void type7Proccess(List<BrokerTransferExcel> brokerLists, List<SavingOrderRecordPo> sourceRecords, String createTime) {
        brokerLists.forEach(broker -> {
            //币种大写
            broker.setCurrency(broker.getCurrency().trim().toUpperCase());
        });

        //按照用户id分组
        Map<Long, List<BrokerTransferExcel>> userIdBrokersMap = brokerLists.stream()
                .collect(Collectors.groupingBy(broker -> broker.getToUserId()));

        Map<Long, List<SavingOrderRecordPo>> userIdRecordsMap = sourceRecords.stream()
                .collect(Collectors.groupingBy(SavingOrderRecordPo::getUserId));


        List<Long> userIdsBroker = new ArrayList<>(userIdBrokersMap.keySet());
        List<Long> userIdsRecord = new ArrayList<>(userIdRecordsMap.keySet());

        if (userIdsBroker.size() != userIdsRecord.size()) {
            LogUtils.writeByFileWrite(createTime + " 支出划转币币 broker记录和挖矿宝记录用户数量不一致----" +
                    "brokerCount=" + userIdsBroker.size() + "," +
                    "recordCount=" + userIdsRecord.size());
            for (Long aLong : userIdsBroker) {
                if (!userIdsRecord.contains(aLong)) {
                    LogUtils.writeByFileWrite(createTime + " 支出划转币币 broker记录和挖矿宝记录用户数量不一致 用户id=" + aLong + " broker有记录，挖矿宝没有记录");
                }
            }
            for (Long aLong : userIdsRecord) {
                if (!userIdsBroker.contains(aLong)) {
                    LogUtils.writeByFileWrite(createTime + " 支出划转币币 broker记录和挖矿宝记录用户数量不一致 用户id=" + aLong + " 挖矿宝有记录，broker没有记录");
                }
            }

        }


        //遍历broker数据
        userIdBrokersMap.keySet().forEach(userId -> {
            //获取该用户该日期的所有broker数据
            List<BrokerTransferExcel> brokerTransferExcels = userIdBrokersMap.get(userId);

            List<SavingOrderRecordPo> orderRecordPos = userIdRecordsMap.containsKey(userId) ? userIdRecordsMap.get(userId) : new ArrayList<>();

            if (brokerTransferExcels.size() != orderRecordPos.size()) {
                LogUtils.writeByFileWrite(createTime + " 支出划转币币 同一用户id=" + userId + "，broker记录和挖矿宝记录数量不一致 brokerCount=" + brokerTransferExcels.size() + ",recordCount=" + orderRecordPos.size());
            }

            //按照币种分组
            Map<String, List<BrokerTransferExcel>> currencyBrokersMap = brokerTransferExcels.stream().collect(Collectors.groupingBy(BrokerTransferExcel::getCurrency));

            Map<String, List<SavingOrderRecordPo>> currencyRecordsMap = orderRecordPos.stream().collect(Collectors.groupingBy(SavingOrderRecordPo::getCurrency));

            //基于broker用户记录做判断
            currencyBrokersMap.keySet().stream().forEach(currency -> {
                List<BrokerTransferExcel> brokers = currencyBrokersMap.get(currency);
                List<SavingOrderRecordPo> records = currencyRecordsMap.get(currency);
                //1.没有该币种造成丢单
                if (!currencyRecordsMap.containsKey(currency)) {
                    //用户有该币种broker划转记录，挖矿宝没有记录
                    BigDecimal totalAmount = brokers.stream().map(BrokerTransferExcel::getAmount).reduce(BigDecimal::add).get();
                    chongfuchukuan.add(createTime + " 支出划转币币 broker有用户数据，挖矿宝没有用户数据，重复出款: " +
                            "userId=" + userId + "," +
                            "currency=" + currency + "," +
                            "totalAmount=" + AmountUtil.rvZeroAndDot(totalAmount) + "," +
                            "count=" + brokers.size());
                    return;
                }

                //2.有该币种，需要匹配对应金额
                Map<String, Integer> countAmountsBrokerMap = brokers.stream().collect(Collectors.toMap(b -> AmountUtil.rvZeroAndDot(b.getAmount()), b -> 1, (a, b) -> a + b));
                Map<String, Integer> countAmountsRecordsMap = records.stream().collect(Collectors.toMap(b -> AmountUtil.rvZeroAndDot(b.getAmount()), b -> 1, (a, b) -> a + b));

                countAmountsBrokerMap.keySet().stream().forEach(amount -> {
                    Integer brokerCount = countAmountsBrokerMap.get(amount);
                    Integer recordCount = countAmountsRecordsMap.get(amount);

                    if (null == recordCount) {
                        chongfuchukuan.add(createTime + " 支出划转币币 broker有记录，但挖矿宝没有该币种金额下单记录，重复出款: " +
                                "userId=" + userId + "," +
                                "currency=" + currency + "," +
                                "amount=" + amount);
                        return;
                    }

                    if (brokerCount.equals(recordCount)) {
                        return;
                    }
                    if (brokerCount > recordCount) {
                        chongfuchukuan.add(createTime + " 支出划转币币 broker有记录，但记录该金额的笔数比挖矿宝记录条数多，重复出款: " +
                                "userId=" + userId + "," +
                                "currency=" + currency + "," +
                                "amount=" + amount + "," +
                                "brokerCount=" + brokerCount + "," +
                                "recordCount=" + recordCount
                        );
                        return;
                    }
                    if (brokerCount < recordCount) {
                        chongfuchukuan.add(createTime + " 支出划转币币 broker有记录，但记录该金额的笔数比挖矿宝记录条数少，原因未知: " +
                                "userId=" + userId + "," +
                                "currency=" + currency + "," +
                                "amount=" + amount + "," +
                                "brokerCount=" + brokerCount + "," +
                                "recordCount=" + recordCount
                        );
                        return;
                    }
                });

            });

            //基于订单流水分析
            currencyRecordsMap.keySet().stream().forEach(currency -> {
                List<BrokerTransferExcel> brokers = currencyBrokersMap.get(currency);
                List<SavingOrderRecordPo> records = currencyRecordsMap.get(currency);
                if (!currencyBrokersMap.containsKey(currency)) {
                    //用户有该币种broker划转记录，挖矿宝没有记录
                    BigDecimal totalAmount = records.stream().map(SavingOrderRecordPo::getAmount).reduce(BigDecimal::add).get();
                    chongfuchukuan.add(createTime + " 支出划转币币 订单流水有数据，broker没有数据，原因未知: " +
                            "userId=" + userId + "," +
                            "currency=" + currency + "," +
                            "totalAmount=" + AmountUtil.rvZeroAndDot(totalAmount) + "," +
                            "count=" + records.size());
                    return;
                }

                //2.有该币种，需要匹配对应金额
                Map<String, Integer> countAmountsBrokerMap = brokers.stream().collect(Collectors.toMap(b -> AmountUtil.rvZeroAndDot(b.getAmount()), b -> 1, (a, b) -> a + b));
                Map<String, Integer> countAmountsRecordsMap = records.stream().collect(Collectors.toMap(b -> AmountUtil.rvZeroAndDot(b.getAmount()), b -> 1, (a, b) -> a + b));

                countAmountsRecordsMap.keySet().stream().forEach(amount -> {
                    Integer brokerCount = countAmountsBrokerMap.get(amount);
                    if (null == brokerCount) {
                        chongfuchukuan.add(createTime + " 支出划转币币 订单流水有记录，但broker没有该币种金额下单记录，原因未知: " +
                                "userId=" + userId + "," +
                                "currency=" + currency + "," +
                                "amount=" + amount);
                        return;
                    }
                });

            });


        });

        //流水表
        userIdRecordsMap.keySet().forEach(userId -> {
            if(!userIdBrokersMap.containsKey(userId)){
                List<SavingOrderRecordPo> recordPos = userIdRecordsMap.get(userId);
                Map<String, BigDecimal> collect = recordPos.stream().collect(Collectors.toMap(po -> po.getCurrency(), SavingOrderRecordPo::getAmount, BigDecimal::add));
                collect.entrySet().forEach(entry->{
                    chongfuchukuan.add(createTime + " 支出划转币币 流水有数据，broker无数据:" + userId + " currency="+entry.getKey() + ",totalAmount="+AmountUtil.rvZeroAndDot(entry.getValue()));
                });
            }
        });


    }


    private static List<BrokerTransferExcel> buildBrokerList(String fileName) {

        if(fileName.contains(".csv")){
            return HPoiUtils.exeCsv(new File(filePath + fileName), BrokerTransferExcel.getParams(), BrokerTransferExcel.class);
        }



        List<BrokerTransferExcel> exe = ExcelImportUtil.importExcel(new File(filePath + fileName), BrokerTransferExcel.class, new ImportParams());
//        List<BrokerTransferExcel> exe = HPoiUtils.exe(new File(filePath + fileName), BrokerTransferExcel.getParams(), BrokerTransferExcel.class);

        return exe;
    }

    private static List<SavingOrderRecordPo> buildOrderRecords(String fileName) {

        if(fileName.contains(".csv")){
            return HPoiUtils.exeCsv(new File(filePath + fileName),SavingOrderRecordPo.getParams(), SavingOrderRecordPo.class);
        }

        return ExcelImportUtil.importExcel(new File(filePath + fileName), SavingOrderRecordPo.class, new ImportParams());
    }

    private static String switchTransferType(String transferType) {
        switch (transferType) {
            case "466":
//                return "pool-savings-clct-to-expend" ;//归集到支出，xxx
                return "归集到支出";
            case "461":
//                return "pool-savings-expend-to-spot" ;//支出到币币，f_to_user_id
                return "支出到币币";
            case "467":
//                return "pool-savings-interest-to-expend" ;//利息到支出，xxx
                return "利息到支出";
            case "462":
//                return "pool-savings-interest-to-spot" ;//利息到币币，f_to_user_id
                return "利息到币币";
            case "460":
//                return "pool-savings-spot-to-clct" ;//币币到归集，f_from_user_id
                return "币币到归集";
            default:
                LogUtils.writeByFileWrite("=======" + transferType);
                return "未知";
        }
    }
}

