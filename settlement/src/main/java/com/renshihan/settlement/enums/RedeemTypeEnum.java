package com.renshihan.settlement.enums;

import lombok.Getter;

@Getter
public enum RedeemTypeEnum {
    ANY_TIME(0, "随时赎回"),
    MATURITY(1, "到期赎回");
    private int code;
    private String message;

    RedeemTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
