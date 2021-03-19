package com.renshihan.settlement.enums;

import com.google.common.base.CaseFormat;
import lombok.Getter;

public enum ExchangeCurrencyState {

    UNKNOWN(0, "未知"),

    NOT_ONLINE(1, "未上线"),

    ENABLE(2, "启用"),

    DISABLE(3, "停用"),

    ;

    @Getter
    private final Integer code;

    @Getter
    private final String value;

    @Getter
    private final String message;

    ExchangeCurrencyState(Integer code, String message) {
        this.code = code;
        this.value = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());
        this.message = message;
    }

    public static ExchangeCurrencyState find(Integer c) {
        for (ExchangeCurrencyState state : ExchangeCurrencyState.values()) {
            if (state.getCode().equals(c)) {
                return state;
            }
        }
        return UNKNOWN;
    }

    public static ExchangeCurrencyState find(String v) {
        for (ExchangeCurrencyState state : ExchangeCurrencyState.values()) {
            if (state.getValue().equals(v)) {
                return state;
            }
        }
        return UNKNOWN;
    }

}
