package com.renshihan.settlement.enums;

import lombok.Getter;

@Getter
public enum ShelfTypeEnum {
    ACTIVE_MINING(0,"000", "活期挖矿"),
    FIXED_MINING(1,"001", "定期挖矿"),
    LIMIT_TIME_MINING(2, "002","限时挖矿");
    private Integer code;
    String shelfId;
    private String message;

    ShelfTypeEnum(int code, String shelfId,String message) {
        this.code = code;
        this.shelfId=shelfId;
        this.message = message;
    }

    public static ShelfTypeEnum getByCode(Integer code){
        for (ShelfTypeEnum value : ShelfTypeEnum.values()) {
            if(value.getCode().equals(code)){
                return value;
            }
        }
        return null;
    }
}
