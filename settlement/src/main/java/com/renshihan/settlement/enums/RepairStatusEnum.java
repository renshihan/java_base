package com.renshihan.settlement.enums;

import lombok.Getter;

@Getter
public enum RepairStatusEnum {
    //募资状态：0-准备募资；1-募资中（正在募资中）；2-募资已满（代表额度用完）；3-募资结束（代表募资时间结束）
    WAIT(0,"待执行"),
    PROCCESS(1,"执行中"),
    FINISH(2,"执行完成"),
    FAILSE(3,"执行失败"),
    ;

    private Integer code;
    private String message;

    RepairStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
