package com.renshihan.settlement.enums;

import lombok.Getter;

@Getter
public enum SideEnum {
    IN("in","流入"),
    OUT("out","流出")
    ;
    private String code;
    private String message;
    SideEnum(String code, String message) {
        this.code=code;
        this.message=message;
    }
}
