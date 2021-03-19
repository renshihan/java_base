package com.renshihan.settlement.enums;

import lombok.Getter;

@Getter
public enum SysAccountEnum {
    DEMAND_IN_AMOUNT_ACCOUNT("DEMAND_IN_AMOUNT_ACCOUNT", "活期归集账户"),
    REGULAR_IN_AMOUNT_ACCOUNT("REGULAR_IN_AMOUNT_ACCOUNT", "定期归集账户"),
    DEMAND_OUT_AMOUNT_ACCOUNT("DEMAND_OUT_AMOUNT_ACCOUNT", "活期支出账户"),
    REGULAR_OUT_AMOUNT_ACCOUNT("REGULAR_OUT_AMOUNT_ACCOUNT", "定期支出账户"),
    INCOME_IN_OUT_AMOUNT_ACCOUNT("INCOME_IN_OUT_AMOUNT_ACCOUNT", "利息账户"),
    INCOME_ACCOUNT("INCOME_ACCOUNT", "挖矿宝收入归集账户AccountId");
    private String code;
    private String message;

    SysAccountEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public static boolean isLegal(Integer code){
        for (SysAccountEnum value : SysAccountEnum.values()) {
            if(value.getCode().equals(code)){
                return true;
            }
        }
        return false;
    }
    public static SysAccountEnum getSysEnumByCode(String code){
        for (SysAccountEnum value : SysAccountEnum.values()) {
            if(value.getCode().equals(code)){
                return value;
            }
        }

        return null;
    }
}
