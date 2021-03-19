package com.renshihan.settlement.enums;

import lombok.Getter;

@Getter
public enum RepairTypeEnum {
    //募资状态：0-准备募资；1-募资中（正在募资中）；2-募资已满（代表额度用完）；3-募资结束（代表募资时间结束）
    DEMAND_SUBSCRIBE(0,"活期申购补单"),
    REGULAR_SUBSCRIBE(1,"定期申购补单"),
    INTEREST(2,"利息补发"),
    ;

    private Integer code;
    private String message;

    RepairTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
