package com.renshihan.settlement.enums;

import lombok.Getter;

/**
 * @author ming.jia
 * @version 1.0
 * @description 资金清算枚举
 * @date 2020/12/20 17:39
 **/
public interface ClearEnum {

    enum ClearProductType {
        CURRENT(0,"活期"),
        FIXED(1, "定期"),
        ;

        @Getter
        private final Integer value;

        @Getter
        private final String desc;

        ClearProductType(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }

    /**
     * 金额类型
     */
    enum ClearAmountType {
        PRINCIPAL(0,"本金"),
        INTEREST(1, "利息"),
        INTEREST_PENALTY(2, "罚息"),
        AWARD(3, "奖励"),
        LIQUIDATED_DAMAGES(4, "违约金"),
        ;

        @Getter
        private final Integer value;

        @Getter
        private final String desc;

        ClearAmountType(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }

    /**
     * 清算状态
     */
    enum ClearStatus {
        CLEAR_WAITING(0,"待清算"),
        CLEAR_OK(1, "清算成功"),
        CLEAR_ERROR(2, "清算失败"),
        CLEAR_CANCEL(3, "清算撤销"),
        CLEAR_END_WAITING_SETTLEMENT(4, "清算完成待结算"),
        CLEAR_END_SETTLEMENT_ERROR(5, "结算失败"),
        CLEAR_END_SETTLEMENT_OK(6,"结算成功"),
        ;

        @Getter
        private final Integer value;

        @Getter
        private final String desc;

        ClearStatus(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }
}
