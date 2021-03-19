package com.renshihan.settlement.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum SubApiaccountType {



    /**
     * 交易子账户
     */
    TRADE(2, "trade"),

    /**
     * 待还借贷本金子账户
     */
    LOAN(7, "loan"),

    /**
     * 待还借贷利息子账户
     */
    INTEREST(8, "interest"),

    /**
     * 垫付金子账户
     */
    ADVANCE(11, "advance"),

    /**
     * C2C出借账户应收本金子账户
     */
    LENDING(12,"lending"),

    /**
     * C2C出借账户应收利息子账户
     */
    EARNINGS(13,"earnings"),
    ;

    private final Integer code;

    private final String name;

    public static SubApiaccountType getByValue(String value) {
        return Arrays.stream(SubApiaccountType.values()).filter(x -> x.getName().equals(value)).findFirst().orElse(null);
    }

    public static boolean isExist(String value) {
        return Arrays.stream(SubApiaccountType.values()).filter(x -> x.getName().equals(value)).findFirst().isPresent();
    }

}
