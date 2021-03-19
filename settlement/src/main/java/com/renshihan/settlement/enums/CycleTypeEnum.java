package com.renshihan.settlement.enums;

import lombok.Getter;

@Getter
public enum CycleTypeEnum {
    DAY(0, "日"),
    WEEK(1, "周"),
    MONTH(2, "月"),
    YEAR(3, "年");
    private int code;
    private String message;

    CycleTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
