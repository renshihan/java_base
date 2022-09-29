package com.renshihan.settlement.handle;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.collection.ConcurrentHashSet;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.renshihan.commons.util.JsonUtil;
import com.renshihan.settlement.bean.LevelTwoPriceFlowDto;
import com.renshihan.settlement.bean.UserNftFlow;
import com.renshihan.settlement.utils.DateUtil;
import com.renshihan.settlement.utils.HPoiUtils;
import io.netty.util.internal.ConcurrentSet;
import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 具体命中规则：
 * 1）连续操作流水满足122，即操作1-交易，操作2-上架，操作3-上架 这样
 * 2）操作2和操作3的上架操作金额差>10u（币种不同时都会转换成usdt比较）
 * 3）操作1完成交易和操作2上架时间差 <5s
 * 4）操作2上架和操作3上架时间差<10s
 */
public class 二级市场价格不一致赔付订单跑数 {
    private static String filePath = "/Users/bj00078ml/Downloads/nftflows/";
    private static List<LevelTwoPriceFlowDto> resultContainer = new ArrayList<>();
    private static String USDT = "USDT";
    private static Map<String, BigDecimal> rateMap = ImmutableMap.of(
            "ETH", new BigDecimal("1300"),
            "BIT", new BigDecimal("0.478"),
            "XTZ", new BigDecimal("1.438")
    );

    public static void main(String[] args) {
        List<UserNftFlow> userNftFlows = getByBrokerFileName(
                "export_result (1).csv",    //到09-28
                "export_result.csv",    //到09-28
                "export_result (2).csv",
                "export_result (3).csv",
                "export_result (4).csv",
                "export_result (5).csv",
                "export_result (6).csv",
                "export_result (7).csv",
                "export_result (8).csv",
                "export_result (9).csv",
                "export_result (10).csv",
                "export_result (11).csv",    //到09-20
                "export_result (12).csv",
                "export_result (13).csv",
                "export_result (14).csv",
                "export_result (15).csv",
                "export_result (16).csv",    //到09-16
                "export_result (17).csv", "export_result (18).csv", "export_result (19).csv", "export_result (20).csv",
                "export_result (21).csv", "export_result (22).csv", "export_result (23).csv", "export_result (24).csv",
                "export_result (25).csv", "export_result (26).csv", "export_result (27).csv", "export_result (28).csv",
                "export_result (29).csv", "export_result (30).csv", "export_result (31).csv", "export_result (32).csv",
                "export_result (33).csv", "export_result (34).csv", "export_result (35).csv", "export_result (36).csv",
                "export_result (37).csv", "export_result (38).csv", "export_result (39).csv", "export_result (40).csv",
                "export_result (41).csv", "export_result (42).csv", "export_result (43).csv", "export_result (44).csv",
                "export_result (45).csv", "export_result (46).csv", "export_result (47).csv", "export_result (48).csv",
                "export_result (49).csv", "export_result (50).csv", "export_result (51).csv", "export_result (52).csv",
                "export_result (53).csv", "export_result (54).csv", "export_result (55).csv", "export_result (56).csv",
                "export_result (57).csv", "export_result (58).csv", "export_result (59).csv", "export_result (60).csv",
                "export_result (61).csv", "export_result (62).csv", "export_result (63).csv", "export_result (64).csv",
                "export_result (65).csv", "export_result (66).csv", "export_result (67).csv", "export_result (68).csv",
                "export_result (69).csv", "export_result (70).csv", "export_result (71).csv", "export_result (72).csv",
                "export_result (73).csv", "export_result (74).csv", "export_result (75).csv", "export_result (76).csv",
                "export_result (77).csv", "export_result (78).csv", "export_result (79).csv", "export_result (80).csv",
                "export_result (81).csv", "export_result (82).csv", "export_result (83).csv", "export_result (84).csv",
                "export_result (85).csv", "export_result (86).csv", "export_result (87).csv", "export_result (88).csv",
                "export_result (89).csv", "export_result (90).csv", "export_result (91).csv", "export_result (92).csv",
                "export_result (93).csv", "export_result (94).csv", "export_result (95).csv", "export_result (96).csv",
                "export_result (97).csv", "export_result (98).csv", "export_result (99).csv", "export_result (100).csv",
                "export_result (101).csv", "export_result (102).csv", "export_result (103).csv", "export_result (111).csv",
                "export_result (112).csv", "export_result (113).csv", "export_result (114).csv", "export_result (115).csv",
                "export_result (116).csv", "export_result (117).csv", "export_result (118).csv", "export_result (119).csv",
                "export_result (210).csv", "export_result (211).csv", "export_result (212).csv", "export_result (213).csv",
                "export_result (214).csv", "export_result (215).csv", "export_result (216).csv", "export_result (217).csv",
                "export_result (218).csv", "export_result (219).csv", "export_result (220).csv", "export_result (221).csv",
                "export_result (222).csv", "export_result (223).csv", "export_result (224).csv", "export_result (225).csv",
                "export_result (226).csv", "export_result (227).csv", "export_result (228).csv", "export_result (229).csv",
                "export_result (230).csv", "export_result (231).csv", "export_result (232).csv", "export_result (233).csv",
                "export_result (234).csv", "export_result (235).csv", "export_result (236).csv", "export_result (237).csv",
                "export_result (238).csv", "export_result (239).csv", "export_result (240).csv", "export_result (241).csv",
                "export_result (242).csv"
        );
        Long minTime = userNftFlows.stream().map(UserNftFlow::getCreatedAt).min(Long::compare).get();
        Long maxTime = userNftFlows.stream().map(UserNftFlow::getCreatedAt).max(Long::compare).get();

        System.out.println("扫描记录开始时间:"+DateUtil.format(new Date(minTime),"yyyy-MM-dd HH:mm:ss"));
        System.out.println("扫描记录结束时间:"+DateUtil.format(new Date(maxTime),"yyyy-MM-dd HH:mm:ss"));
        handle(userNftFlows);
        if (resultContainer.size() == 0) {
            System.out.println("pass!!!!!");
        } else {
            List<LevelTwoPriceFlowDto> hasEnds = getHasEnd().stream().sorted(Comparator.comparing(LevelTwoPriceFlowDto::getUserId)).collect(Collectors.toList());
            System.out.println("已经处理记录总数:"+hasEnds.size());
//            for (LevelTwoPriceFlowDto hasEnd : hasEnds) {
//                System.out.println("已经赔偿--->"+hasEnd.toString());
//            }
            System.out.println("跑规则命中总数:"+resultContainer.size());
            resultContainer = resultContainer.stream()
                    //过滤已经处理的
                    .filter(levelTwoPriceFlowDto -> {
                        for (LevelTwoPriceFlowDto hasEnd : hasEnds) {
                            if (levelTwoPriceFlowDto.getUserId().equals(hasEnd.getUserId()) &&
                                    levelTwoPriceFlowDto.getViewPrice().compareTo(hasEnd.getViewPrice()) == 0 &&
                                    levelTwoPriceFlowDto.getPayPrice().compareTo(hasEnd.getPayPrice()) == 0
                            ) {
//                                System.out.println("已经处理....DTO=" + levelTwoPriceFlowDto);
                                return false;
                            }
                        }

                        return true;
                    }).sorted(Comparator.comparing(LevelTwoPriceFlowDto::getUserId)).collect(Collectors.toList());
            System.out.println("过滤已经处理剩余待处理总数:"+resultContainer.size());
            String ids = resultContainer.stream()
                    .map(LevelTwoPriceFlowDto::getId).map(id -> "'" + id + "'")
                    .collect(Collectors.joining(","));
            System.out.println("(" + ids + ")");
            System.out.println("===================");
            System.out.println("uid｜用户看到｜用户购买｜应赔偿");
            for (LevelTwoPriceFlowDto levelTwoPriceFlowDto : resultContainer) {
                System.out.println(levelTwoPriceFlowDto.toString());
            }

        }


    }

    public static void handle(List<UserNftFlow> userNftFlows) {
        //按照nft_id分组
        Map<Long, List<UserNftFlow>> nftIdMap = userNftFlows.stream().collect(Collectors.groupingBy(UserNftFlow::getNftId));
        int beforeNftIdCount = nftIdMap.size();
        List<Long> nftIds = nftIdMap.keySet().stream()
                .filter(nftId -> nftIdMap.get(nftId).size() > 2)    //流水条数大于2，最小是3条
                .filter(nftId -> {
                    Set<Integer> types = nftIdMap.get(nftId).stream().map(UserNftFlow::getType).collect(Collectors.toSet());
                    //只有有交易类型的才可以进行
                    return types.contains(1);
                })
                .collect(Collectors.toList());
        System.out.println(String.format("beforeNftIdCount=%s filter after is =%s", beforeNftIdCount, nftIds.size()));

        for (Long nftId : nftIds) {
            //1.获取流水记录，且按照时间倒序
            List<UserNftFlow> flows = nftIdMap.get(nftId).stream().sorted(Comparator.comparing(UserNftFlow::getChangeAt).reversed()).collect(Collectors.toList());
//            UserNftFlow first = flows.get(0);
//            UserNftFlow last = flows.get(flows.size()-1);
//            String firstTime= DateUtil.format(new Date(first.getCreatedAt()),"yyyy-MM-dd HH:mm:ss");
//            String lastTime= DateUtil.format(new Date(last.getCreatedAt()),"yyyy-MM-dd HH:mm:ss");
//            System.out.println(String.format("firstTime=%s lastTime=%s",firstTime,lastTime));
            check(nftId, flows);
        }

    }

    public static void check(Long nftId, List<UserNftFlow> flows) {
        int lastIndex = flows.size() - 1;
        for (int i = 0; i < flows.size(); i++) {

            int towIndex = i + 1;
            int thirdIndex = i + 2;
            UserNftFlow one = flows.get(i);
            UserNftFlow two = towIndex > lastIndex ? null : flows.get(towIndex);
            UserNftFlow third = thirdIndex > lastIndex ? null : flows.get(thirdIndex);
            boolean isPay = isPay(one, two,third);

            if (isPay) {
                resultContainer.add(new LevelTwoPriceFlowDto()
                        .setId(one.getId())
                        .setUserId(Long.valueOf(one.getToUserId()))
                        .setViewPrice(convertToUSDT(third.getCurrency(),third.getPrice()))
                        .setPayPrice(convertToUSDT(two.getCurrency(),two.getPrice()))
                        .setLossPrice(convertToUSDT(two.getCurrency(),two.getPrice()).subtract(convertToUSDT(third.getCurrency(),third.getPrice())))
                );
                System.out.println(String.format("nftId=%s is pay--->%s", nftId, JsonUtil.toJson(one)));
            }
        }
    }

    public static boolean isPay(UserNftFlow one, UserNftFlow two, UserNftFlow third) {
        if (null == two) {
            return false;
        }
        if (null == third) {
            return false;
        }
        //1交易；2上架；2上架
        if (one.getType() == 1 && two.getType() == 2 && third.getType() == 2) {
            //这里加具体判断依据
            return checkPrice(two, third) && //价格规则命中
                    checkTime(one, two, third)    //时间规则命中
                    ;
        }

        return false;
    }

    public static boolean checkPrice(UserNftFlow two, UserNftFlow third) {
        BigDecimal towPrice = convertToUSDT(two.getCurrency(), two.getPrice());
        BigDecimal thirdPrice = convertToUSDT(third.getCurrency(), third.getPrice());

        boolean isHit = towPrice.subtract(thirdPrice).compareTo(new BigDecimal("10")) > 0;
        if (isHit) {
//            System.out.println(String.format("checkPrice--->nftId=%s twoId=%s thirdId=%s is hit!!!!",two.getNftId(),two.getId(),third.getId()));
        }
        return isHit;
    }

    public static boolean checkTime(UserNftFlow one, UserNftFlow two, UserNftFlow third) {
        long oneTime = one.getChangeAt();
        long twoTime = two.getChangeAt();
        long thirdTime = third.getChangeAt();

        if ((oneTime - twoTime) > 5000L) {
            return false;
        }

        if ((twoTime - thirdTime) > 10000L) {
            return false;
        }
//        System.out.println(String.format("checkTime--->nftId=%s oneId=%s twoId=%s thirdId=%s is hit!!!!",two.getNftId(),one.getId(),two.getId(),third.getId()));
        return true;
    }


    public static BigDecimal convertToUSDT(String currency, BigDecimal price) {
        if (currency.equalsIgnoreCase(USDT)) {
            return price;
        }
        try {
            BigDecimal multiply = price.multiply(rateMap.get(currency.toUpperCase()));
            return multiply;
        } catch (Exception e) {

            throw e;
        }


    }

    private static List<UserNftFlow> getByBrokerFileName(String... brokerFileNames) {
        List<UserNftFlow> userNftFlows = new ArrayList<>();
        for (String brokerFileName : brokerFileNames) {
//            System.out.println("开始倒入----fileName=" + brokerFileName);
            List<UserNftFlow> transferExcels = buildBrokerList(brokerFileName);
            userNftFlows.addAll(transferExcels);
//            System.out.println("导入完成----fileName=" + brokerFileName + "，导入总数-->" + transferExcels.size());
        }

        return userNftFlows;
    }


    private static List<UserNftFlow> buildBrokerList(String fileName) {

        if (fileName.contains(".csv")) {
            return HPoiUtils.exeCsv(new File(filePath + fileName), UserNftFlow.getParams(), UserNftFlow.class);
        }


        List<UserNftFlow> exe = ExcelImportUtil.importExcel(new File(filePath + fileName), UserNftFlow.class, new ImportParams());
//        List<BrokerTransferExcel> exe = HPoiUtils.exe(new File(filePath + fileName), BrokerTransferExcel.getParams(), BrokerTransferExcel.class);

        return exe;
    }

    private static List<LevelTwoPriceFlowDto> getHasEnd() {
        String a = "      12842541\n" +
                "        3\n" +
                "        8\n" +
                "        5\n" +
                "        12842541\n" +
                "        3\n" +
                "        7\n" +
                "        4\n" +
                "        12842541\n" +
                "        3\n" +
                "        8.99\n" +
                "        5.99\n" +
                "        12842541\n" +
                "        3\n" +
                "        5\n" +
                "        2\n" +
                "        35333117\n" +
                "        3\n" +
                "        10\n" +
                "        7\n" +
                "        32184965\n" +
                "        2.5\n" +
                "        25\n" +
                "        22.5\n" +
                "        28374094\n" +
                "        2.02\n" +
                "        20\n" +
                "        17.98\n" +
                "        12535332\n" +
                "        2.02\n" +
                "        23\n" +
                "        20.98\n" +
                "        19229145\n" +
                "        2.55\n" +
                "        25.5\n" +
                "        22.95\n" +
                "        19055634\n" +
                "        1.9\n" +
                "        10\n" +
                "        8.1\n" +
                "        9810291\n" +
                "        2.01\n" +
                "        20.1\n" +
                "        18.09\n" +
                "        30739899\n" +
                "        2.01\n" +
                "        20.1\n" +
                "        18.09\n" +
                "        747070\n" +
                "        2.01\n" +
                "        20.1\n" +
                "        18.09\n" +
                "        7245523\n" +
                "        2.01\n" +
                "        20.1\n" +
                "        18.09\n" +
                "        27858434\n" +
                "        2.01\n" +
                "        20.1\n" +
                "        18.09\n" +
                "        33963902\n" +
                "        2.12\n" +
                "        21.5\n" +
                "        19.38\n" +
                "        27802515\n" +
                "        1.87\n" +
                "        18.7\n" +
                "        16.83\n" +
                "        7245523\n" +
                "        1.75\n" +
                "        17.2\n" +
                "        15.45\n" +
                "        12457481\n" +
                "        2.21\n" +
                "        22.5\n" +
                "        20.29\n" +
                "        17169516\n" +
                "        2.88\n" +
                "        28.8\n" +
                "        25.92\n" +
                "        28820199\n" +
                "        3.18\n" +
                "        31.8\n" +
                "        28.62\n" +
                "        35062351\n" +
                "        2.88\n" +
                "        28.8\n" +
                "        25.92\n" +
                "        28227674\n" +
                "        3.12\n" +
                "        11.3\n" +
                "        8.18\n" +
                "        31382664\n" +
                "        2.86\n" +
                "        14.9\n" +
                "        12.04\n" +
                "        22785823\n" +
                "        2.22\n" +
                "        22.2\n" +
                "        19.98\n" +
                "        12439409\n" +
                "        3\n" +
                "        20\n" +
                "        17\n" +
                "        12842541\n" +
                "3\n" +
                "12\n" +
                "9\n" +
                "21020517\n" +
                "3.20\n" +
                "10\n" +
                "6.80\n" +
                "21844750\n" +
                "2\n" +
                "6\n" +
                "4\n" +
                "35333117\n" +
                "3\n" +
                "10\n" +
                "7";

        List<String> collect = Arrays.stream(a.split("\n")).map(String::trim).collect(Collectors.toList());

        List<List<String>> partition = Lists.partition(collect, 4);

        List<LevelTwoPriceFlowDto> flowDtos = partition.stream().map(infos -> {
            LevelTwoPriceFlowDto dto = new LevelTwoPriceFlowDto();
            dto.setUserId(Long.valueOf(infos.get(0)));
            dto.setViewPrice(new BigDecimal(infos.get(1)));
            dto.setPayPrice(new BigDecimal(infos.get(2)));
            dto.setLossPrice(dto.getPayPrice().subtract(dto.getViewPrice()));
            return dto;
        }).collect(Collectors.toList());

        return flowDtos;
    }
}
