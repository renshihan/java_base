package com.renshihan.settlement.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum UserType {

    // 普通用户
    NORMAL(1),

    SUB_USER(2),

    SHADOW_USER(3);

    private static final UserType[] values = values();

    @Getter
    @JsonValue
    private final Integer code;

    UserType(Integer code) {
        this.code = code;
    }

    @JsonCreator
    public static UserType of(Integer code) {
        for (UserType value : values) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return NORMAL;
    }
}
