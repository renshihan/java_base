package com.renshihan.settlement.enums;

import lombok.Getter;

@Getter
public enum ActiveTypeEnum {
    //募资状态：0-准备募资；1-募资中（正在募资中）；2-募资已满（代表额度用完）；3-募资结束（代表募资时间结束）
    NOLMAL(0,"准备募资"),
    NEW_YEAR(1,"春节活动");
    private Integer code;
    private String message;

    ActiveTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
