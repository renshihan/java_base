package com.renshihan.settlement.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum AccountType {
    /**
     * 未知账户
     */
    UNKOWN(-1, "unkown"),

    /**
     * 系统账户
     */
    SYSTEM(1, "system"),

    /**
     * 现货账户
     */
    SPOT(2, "spot"),

    /**
     * 杠杆账户
     */
    MARGIN(3, "margin"),

    OTC(4, "otc"),

    POINT(5, "point"),

    MINEPOOL(6, "minepool"),

    ETF(7, "etf"),

    AGENCY(8, "agency"),

    /**
     * 全仓杠杆账户
     */
    SUPER_MARGIN(9, "super-margin"),

    BORROW(10, "borrow"),
    INVESTMENT(11, "investment"),

    SAVINGS(12,"deposit-earning"),
    ;

    private final Integer code;

    private final String value;

    public static AccountType findByValue(String value) {
        return Arrays.stream(AccountType.values()).filter(x -> x.value.equals(value)).findFirst().orElse(UNKOWN);
    }

    /**
     * 是否是现货账户
     *
     * @return
     */
    public boolean isSpot() {
        return this == SPOT;
    }

    /**
     * 是否是 C2C借款账户
     *
     * @return
     */
    public boolean isBORROW() {
        return this == BORROW;
    }


    /**
     * 是否是 C2C账户
     *
     * @return
     */
    public static boolean checkC2CAccount(String value) {

        if (value.equals(BORROW.getValue()) || value.equals(INVESTMENT.getValue())) {
            return true;
        }
        return false;

    }
}
