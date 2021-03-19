package com.renshihan.settlement.enums;

import lombok.Getter;

@Getter
public enum ProjectTypeEnum {
    DEMAND(0, "活期"),
    REGULAR(1, "定期");
    private Integer code;
    private String message;

    ProjectTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public static boolean isLegal(Integer code){
        for (ProjectTypeEnum value : ProjectTypeEnum.values()) {
            if(value.getCode().equals(code)){
                return true;
            }
        }
        return false;
    }
}
