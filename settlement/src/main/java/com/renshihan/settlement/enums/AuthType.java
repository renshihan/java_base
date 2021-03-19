package com.renshihan.settlement.enums;

/**
 * @since 2019-12-08
 */
public enum AuthType {

    /**
     * 进行用户校验，同时支持匿名访问
     */
    DEFAULT,

    /**
     * 不进行用户校验，无法获取 req userId
     */
    ANONYM,

    /**
     * 支持轻登录模式
     */
    LITE,

    /**
     * 只能通过 api 方式访问
     */
    API_ONLY
    ;

    public boolean checkToken() {
        return this == DEFAULT || this == LITE;
    }

    public boolean allowAnonym() {
        return this == DEFAULT || this == ANONYM;
    }

    public boolean allowLite() {
        return this == LITE;
    }
}
