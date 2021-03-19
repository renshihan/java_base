package com.renshihan.settlement.enums;

import com.google.common.base.CaseFormat;
import lombok.Getter;

public enum ExchangeState {

    UNKNOWN(0, "未知"),

    NOT_ONLINE(1, "未上线"),

    ONLINE(2, "在线"),

    OFFLINE(3, "离线"),

    ;

    @Getter
    private final Integer code;

    @Getter
    private final String value;

    @Getter
    private final String message;

    ExchangeState(Integer code, String message) {
        this.code = code;
        this.value = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());
        this.message = message;
    }

    public static ExchangeState find(Integer c) {
        for (ExchangeState state : ExchangeState.values()) {
            if (state.getCode().equals(c)) {
                return state;
            }
        }
        return UNKNOWN;
    }

    public static ExchangeState find(String v) {
        for (ExchangeState state : ExchangeState.values()) {
            if (state.getValue().equals(v)) {
                return state;
            }
        }
        return UNKNOWN;
    }

    public boolean isOnline() {
        return ONLINE.equals(this);
    }

}
