package com.renshihan.settlement.enums;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ming.jia
 * @version 1.0
 * @description 订单状态枚举
 * @date 2020/12/19 18:49
 **/
public interface OrderEnum {

    /**
     * 产品枚举
     */
    enum ProductTypeEnum {
        FLEXIBLE(0, "活期"),
        FIXED(1, "定期"),
        ;

        @Getter
        private final Integer value;

        @Getter
        private final String desc;

        ProductTypeEnum(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

    }

    /**
     * 订单状态
     */
    enum OrderStatusEnum {

        ORDER_INIT(0, "新建状态"),
        ORDER_OK(1, "成功"),
        ORDER_ERROR(2, "失败"),
        ORDER_END(3, "订单结束"),
        ;

        @Getter
        private final Integer value;

        @Getter
        private final String desc;

        OrderStatusEnum(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }

    /**
     * 挖矿状态
     */
    enum OrderMiningStatusEnum {
        ORDER_MINING_PREPARE(0, "预挖矿"),
        ORDER_MINING_DOING(1, "挖矿中"),
        ORDER_MINING_OK(2, "挖矿完成"),
        ORDER_MINING_STOP(3, "停止挖矿"),
        ORDER_MINING_END(4, "挖矿结束"),
        ;
        @Getter
        private final Integer value;

        @Getter
        private final String desc;

        OrderMiningStatusEnum(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }

    /**
     * 来源类型
     */
    enum OrderSourceTypeEnum {
        ORDER_SOURCE_TYPE_USER(0,460, "主动（用户主动下单）"),
        ORDER_SOURCE_TYPE_FIXED_TO_CURRENT(1, 460,"自动定转活"),
        ORDER_SOURCE_TYPE_BALANCE(2, 460,"余额自动挖矿"),
        MATURITY_REDEEM(3, 461,"到期赎回"),
        ANY_TIME_REDEEM(4, 461,"随时赎回"),
        INCOME_SEND(5, 462,"收益发放"),
        INCOME_REINVEST(6, 467,"收益复投"),
        COIN_DEPOSIT_TREASURE(7,460 ,"存币宝升级转入"),
        TEST(8,null, "测试插入（方便删除）"),
        FIXED_INCOME_ROLL_IN(9,null ,"定期利息支出");
        @Getter
        private final Integer value;

        @Getter
        private final Integer brokerType;

        @Getter
        private final String desc;

        OrderSourceTypeEnum(Integer value,Integer brokerType, String desc) {
            this.value = value;
            this.brokerType=brokerType;
            this.desc = desc;
        }

        public static OrderSourceTypeEnum getByValue(Integer value) {
            for (OrderSourceTypeEnum orderSourceTypeEnum : OrderSourceTypeEnum.values()) {
                if (orderSourceTypeEnum.getValue().equals(value)) {
                    return orderSourceTypeEnum;
                }
            }
            return null;
        }
        public static List<Integer> getByBrokerType(Integer brokerType) {
            List<Integer> orderSourceTypes=new ArrayList<>();
            for (OrderSourceTypeEnum orderSourceTypeEnum : OrderSourceTypeEnum.values()) {
//                System.out.println("----brokerType-"+brokerType + ",brokerType="+orderSourceTypeEnum.getBrokerType()+"sourceType="+ JSONObject.toJSONString(orderSourceTypes));
                if (null!=orderSourceTypeEnum.getBrokerType() &&orderSourceTypeEnum.getBrokerType().equals(brokerType)) {

                    orderSourceTypes.add(orderSourceTypeEnum.getValue());
                }
            }
            return orderSourceTypes;
        }
    }

    /**
     * 定期自动转活
     */
    enum OrderFixedToCurrent {
        ORDER_FIXED_TO_CURRENT_NO(0, "关"),
        ORDER_FIXED_TO_CURRENT_YES(1, "开"),
        ;
        @Getter
        private final Integer value;

        @Getter
        private final String desc;

        OrderFixedToCurrent(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }


    /**
     * 定转活是否成功
     */
    enum OrderFixedToCurrentIsSuccess {

        ORDER_FIXED_TO_CURRENT_DEFAULT(0, "默认状态"),
        ORDER_FIXED_TO_CURRENT_SUCCESS(1, "定转活成功"),
        ORDER_FIXED_TO_CURRENT_FAIL(2, "定转活失败"),
        ;

        @Getter
        private final Integer value;

        @Getter
        private final String desc;

        OrderFixedToCurrentIsSuccess(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }

    /**
     * 赎回状态
     */
    enum OrderRedeemStatus {
        //1-成功；2-失败；3-失败待重试
        SUCCESS(1, "Success"),
        FAILURE(2, "Failure"),
        FAIL_RETRY(3, "Fail_Retry"),

        ;
        @Getter
        private final Integer value;

        @Getter
        private final String desc;

        OrderRedeemStatus(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }

    /**
     * 是否增加或者减掉账户金额
     */
    enum userAccountDeal {
        ADD(1, "add"),
        SUBTRACT(2, "subtract"),
        ;
        @Getter
        private final Integer value;

        @Getter
        private final String desc;

        userAccountDeal(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }

}
