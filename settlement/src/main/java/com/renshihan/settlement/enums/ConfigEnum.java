package com.renshihan.settlement.enums;

import com.google.common.base.CaseFormat;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum ConfigEnum {

    UNKNOWN(0, "未知"),

    ALL(1, "all"),

    EXCHANGE(2, "交易所"),

    CURRENCY(3, "币种"),

    SYMBOL(4, "交易对", CURRENCY),

    BLACKLIST(5, "黑名单"),

    CHAIN(6, "区块链", CURRENCY),

    SYMBOL_FEE(7, "交易手续费", CURRENCY, SYMBOL),

    APP_AUDIT(8, "APP提审"),
    ;

    public static final int ORDER_EXCHANGE = 2;
    public static final int ORDER_CURRENCY = 3;
    public static final int ORDER_SYMBOL = 4;
    public static final int ORDER_BLACKLIST = 5;
    public static final int ORDER_CHAIN = 6;
    public static final int ORDER_SYMBOL_FEE = 7;
    public static final int ORDER_APP_AUDIT = 8;

    static {
        for (ConfigEnum config : values()) {
            config.subscription.add(config);
            config.subscription.add(ALL);
        }
    }

    @Getter
    private final Integer code;

    @Getter
    private final String value;

    @Getter
    private final String message;

    private final Set<ConfigEnum> subscription;

    ConfigEnum(Integer code, String message, ConfigEnum... depends) {
        this.code = code;
        this.value = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());
        this.message = message;

        subscription = new HashSet<>(Arrays.asList(depends));
    }

    public static ConfigEnum find(Integer c) {
        for (ConfigEnum state : ConfigEnum.values()) {
            if (state.getCode().equals(c)) {
                return state;
            }
        }
        return UNKNOWN;
    }

    public static ConfigEnum find(String v) {
        for (ConfigEnum state : ConfigEnum.values()) {
            if (state.getValue().equals(v)) {
                return state;
            }
        }
        return UNKNOWN;
    }

    public boolean isSubscribed(ConfigEnum eventType) {
        return subscription.contains(eventType);
    }

}
