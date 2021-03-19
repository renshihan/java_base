package com.renshihan.settlement.enums;

import lombok.Getter;

/**
 * @author ming.jia
 * @version 1.0
 * @description 资金结算枚举
 * @date 2020/12/20 19:17
 **/
public interface SettlementEnum {

    /**
     * 结算状态
     */
    enum SettlementStatus {
        INIT(0,"初始态"),
        PRINCIPAL_OK(1, "本金结算成功"),
        PRINCIPAL_ERROR(2, "本金结算失败"),
        INTEREST_OK(3,"利息结算成功"),
        INTEREST_ERROR(4,"利息结算失败"),
        SETTLEMENT_OK(5,"结算成功"),
        SETTLEMENT_ERROR(6,"结算失败")
        ;

        @Getter
        private final Integer value;

        @Getter
        private final String desc;

        SettlementStatus(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }
}
