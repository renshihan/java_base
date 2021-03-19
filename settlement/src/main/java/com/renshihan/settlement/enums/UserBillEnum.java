package com.renshihan.settlement.enums;

import lombok.Getter;

/**
 * @author ming.jia
 * @version 1.0
 * @description 用户账单枚举
 * @date 2020/12/20 19:41
 **/
public interface UserBillEnum {

    /**
     * 用户账单状态
     */
    enum UserBillStatus {
        STATUS_OK(0,"已付款"),
        STATUS_ERROR(1, "未付款"),
        ;

        @Getter
        private final Integer value;

        @Getter
        private final String desc;

        UserBillStatus(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }

    /**
     * 用户账单状态
     */
    enum UserBillAmountType {
        PRINCIPAL(0,"本金"),
        INTEREST(1, "利息"),
        ;

        @Getter
        private final Integer value;

        @Getter
        private final String desc;

        UserBillAmountType(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }
}
