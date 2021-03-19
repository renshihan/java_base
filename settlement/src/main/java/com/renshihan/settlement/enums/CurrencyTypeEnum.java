package com.renshihan.settlement.enums;

import lombok.Getter;

@Getter
public enum CurrencyTypeEnum {
    DIGITAL("digital","数币"),
    LEGAL("legal","法币");
    ;
    private String code;
    private String name;
    CurrencyTypeEnum(String code, String name) {
        this.code=code;
        this.name=name;
    }

}
