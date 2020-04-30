package com.renshihan.study.table.klks;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class TableMsg {
    @Data
    class Node {
        private String name;
        private String desc;

    }

    public TableMsg put(String name, String desc) {
        name = name.toLowerCase();
        List<Node> nodes = depositoryMap.get(name);
        if (null == nodes) {
            nodes = new ArrayList<>();
            depositoryMap.put(name, nodes);
        }
        boolean isExist = false;
        for (Node node : nodes) {
            if (node.getName().equals(name)) {
                node.setDesc(desc);
                isExist = true;
            }
        }
        if (!isExist) {
            Node node = new Node();
            node.setName(name);
            node.setDesc(desc);
            nodes.add(node);
        }
        return this;
    }

    Map<String, List<Node>> depositoryMap = new HashMap<>();

    //    public static void main(String[] args) {
//        TableMsg tableMsg = new TableMsg();
//        tableMsg
//                .put("parent_id","父级id")
//                .put("id_type","证件类型（1:身份证）")
//                .put("id_number","证件号码")
//                .put("name","真实姓名")
//                .put("age","父级id")
//                .put("sign_time","签约时间")
//                .put("check_status","实名认证状态（0未实名认证，1已实名认证）")
//                .put("create_time","")
//                .put("user_label_ids","")
//                .put("id_label","身份标识 0 无身份,1 团长,2 盟友")
//                .put("id_epos_lable","电签身份标识 0 无身份,2 盟友 5电签团长 6 电签营长 7电签连长 8电签排长")
//                .put("mer_flag","是否为商户 0否,1是,默认0")
//                .put("ally_flag","是否为盟友 0否1是,默认0")
//                .put("","")
//                .put("","")
//                .put("","");
//
//    }
    public static void main(String[] args) {
        Map<String, BigDecimal> userAmounts = new HashMap<>();
        BigDecimal zuizhongA=new BigDecimal(" 130623.0000");
        userAmounts.put("ff8080816c428203016c6f29143e661a",new BigDecimal("4800.0000"));
        userAmounts.put("59342144e2ac42768774f5fbf763fba0",new BigDecimal("10299.0000"));
        userAmounts.put("a0b05017b224423989e74826306d6410",new BigDecimal("22411.0000"));
        userAmounts.put("0aa7175b1be44b4b9bab4eb1241534b3",new BigDecimal("2000.0000"));
        userAmounts.put("06e79ea755b9406a8c9623bd79d64929",new BigDecimal("14820.0000"));
        userAmounts.put("61b500c9edbd40feb7d4454de457f6ac",new BigDecimal("8207.0000"));
        userAmounts.put("5ca00c218dc7448bb0caf008d4ed1ab8",new BigDecimal("3300.0000"));
        userAmounts.put("f50d7c0904524054a9e820c0f83e7ab1",new BigDecimal("4020.0000"));
        userAmounts.put("a224a46afa644a5895b437810d5999e1",new BigDecimal("6333.0000"));
        userAmounts.put("c9bf8d168cd042c08e1d3de677b1b781",new BigDecimal("9100.0000"));
        userAmounts.put("eeafdd930eaa44a082fd3ca50688fb33",new BigDecimal("9074.0000"));
        userAmounts.put("52fbe968e3da46488b18cddc700eb9a1",new BigDecimal("891.0000"));
        userAmounts.put("508ba22eb0c04723ba75c242163ddbed",new BigDecimal("11370.0000"));
        userAmounts.put("38a139d197ea447489b5ad717bb0eba7",new BigDecimal("8790.0000"));
        userAmounts.put("ee0064cc897f4205b122bb7390a5bc15",new BigDecimal("21.0000"));
        userAmounts.put("7c3df517a56c4ada8ce9c21102f97d59",new BigDecimal("5983.0000"));
        userAmounts.put("7e270aa8d8a94938b7ce4335806694e7",new BigDecimal("2998.0000"));
        userAmounts.put("08140fdc6b3f4580abab9b6ba0ee11dd",new BigDecimal("6206.0000"));


    }
}
