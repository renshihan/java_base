package com.renshihan.settlement.enums;


import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SwitchStatusEnum {

    CLOSE(0, "关"),

    OPEN(1, "开");
    private Integer code;
    private String messagep;

    SwitchStatusEnum(Integer code, String message) {
        this.code = code;
        this.messagep = message;
    }

    public static boolean isLegal(int code) {
        for (SwitchStatusEnum value : SwitchStatusEnum.values()) {
            if (value.getCode()==code) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotLegal(int code) {
        return !isLegal(code);
    }
    public static SwitchStatusEnum getByCode(int status){
        return Arrays.stream(SwitchStatusEnum.values()).filter(e->e.getCode()==status).findFirst().get();
    }

}
