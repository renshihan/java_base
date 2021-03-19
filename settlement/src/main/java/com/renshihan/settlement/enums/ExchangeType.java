package com.renshihan.settlement.enums;

import com.google.common.base.CaseFormat;
import lombok.Getter;

import java.util.Map;

public enum ExchangeType {

    UNKNOWN(0, "未知"),

    DEFAULT(1, "默认"),

    CLOUD(2, "火币云"),

    LOCAL(3, "本地站"),

    ;

    public static final String ALLOW_VALUES = "cloud,local";

    public static final String ALLOW_PATTERN = "cloud|local";


    @Getter
    private final Integer code;

    @Getter
    private final String value;

    @Getter
    private final String message;

    ExchangeType(Integer code, String message) {
        this.code = code;
        this.value = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());
        this.message = message;
    }

}
