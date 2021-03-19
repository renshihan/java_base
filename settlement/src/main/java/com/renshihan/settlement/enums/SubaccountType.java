package com.renshihan.settlement.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum SubaccountType {

    /**
     * 系统子账户
     */
    SYSTEM(1, "system"),

    /**
     * 交易子账户
     */
    TRADE(2, "trade"),

    /**
     * 冻结子账户，被子账户的suspense属性代替
     */
    FROZEN(3, "frozen"),

    /**
     * 预留
     */
    RESERVED(4, "reserved"),

    /**
     * 清算子账户
     */
    CLEARING(5, "clearing"),

    /**
     * 欠费账户，已下线
     */
    CREDIT(6, "credit"),

    /**
     * 待还借贷本金子账户
     */
    LOAN(7, "loan"),

    /**
     * 待还借贷利息子账户
     */
    INTEREST(8, "interest"),

    /**
     * 锁仓子账户
     */
    LOCK(9, "lock"),

    /**
     * 储蓄子账户
     */
    BANK(10, "bank"),

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

    public static SubaccountType getByValue(String value) {
        return Arrays.stream(SubaccountType.values()).filter(x -> x.getName().equals(value)).findFirst().orElse(null);
    }

}
