package com.renshihan.settlement.enums;

import lombok.Getter;

@Getter
public enum OrderRecordStatusEnum {
    WAIT(0, "新建待处理"),
    SUCCESS(1, "成功"),
    FAIL(2, "失败"),
    WAIT_TRY(3, "失败等重试"),
    PROCCESS(4,"处理中");
    private Integer code;
    private String message;

    OrderRecordStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
