package com.renshihan.settlement.enums;

import lombok.Getter;

import java.util.Locale;

@Getter
public enum CurrencyEnum {
    BTC("BTC","BTC"),
    USDT("USDT","USDT"),
    CNY("CNY","人命币"),
    USD("USD","美元"),
    EUR("EUR","欧元"),
    TRY("TRY","里拉"),
    GBP("GBP","英镑"),
    VND("VND","越南盾"),
    HKD("HKD","港元"),
    TWD("TWD","新台币"),
    MYR("MYR","马来西亚令吉"),
    KRW("KRW","韩币"),
    RUB("RUB","卢布"),
    SDG("SDG","新加坡元")
    ;
    private String currency;
    private String name;
    CurrencyEnum(String currency,String name) {
        this.currency=currency;
        this.name=name;
    }

    public static CurrencyEnum getByCurrency(String currency){
        if(null==currency||"".equals(currency)){
            return null;
        }
        currency=currency.toUpperCase(Locale.ROOT);
        for (CurrencyEnum value : CurrencyEnum.values()) {
            if(value.getCurrency().equals(currency)){
                return value;
            }
        }
        return null;
    }

}
