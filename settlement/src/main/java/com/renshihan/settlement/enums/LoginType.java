package com.renshihan.settlement.enums;



import java.util.Map;

public enum LoginType {
    NORMAL(1, "正常登录"),
    LITE(2, "轻登录");



    private final Integer code;
    private final String type;

    LoginType(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

}
