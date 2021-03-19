package com.renshihan.settlement.enums;

/**
 * @since 2019-12-08
 */
public enum AuthScope {

    /**
     * 默认为Global接口
     */
    DEFAULT,

    /**
     * 云交易所基础业务
     */
    CLOUD,

    /**
     * 子账号可以访问
     */
    SUB_USER,

    /**
     * 火币云或子账号可以访问
     */
    CLOUD_SUB_USER,

    ;

    public boolean allowCloud() {
        return this == CLOUD || this == CLOUD_SUB_USER;
    }

    public boolean allowSubUser() {
        return this == SUB_USER || this == CLOUD_SUB_USER;
    }
}
