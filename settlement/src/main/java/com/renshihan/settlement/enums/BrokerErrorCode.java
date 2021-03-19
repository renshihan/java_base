package com.renshihan.settlement.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Broker错误码
 */
public enum BrokerErrorCode {
    /**
     * 余额不足
     */
    ACCOUNT_BALANCE_INSUFFICIENT("broker.account-balance-insufficient-error"),

    ACCOUNT_DW_BALANCE_INSUFFICIENT("broker.dw-insufficient-balance"),

    ACCOUNT_TRANSFER_BALANCE_INSUFFICIENT ("broker.account-transfer-balance-insufficient-error"),

    ACCOUNT_TRANSFER_FROM_SUBACCOUNT_INEXISTENT ("broker.account-transfer-from-subaccount-inexistent-error"),

    /**
     * broker内部错误
     */
    BROKER_CALL_GATEWAY_INTERNAL_ERROR("broker.gateway-internal-error")
    ;

    private final String code;

    private static final Map<String, BrokerErrorCode> CODE_MAP = new HashMap<>();
    static {
        for (BrokerErrorCode brokerErrorCode : BrokerErrorCode.values()) {
            CODE_MAP.put(brokerErrorCode.getCode(), brokerErrorCode);
        }
    }

    BrokerErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BrokerErrorCode findByCode(String code) {
        return CODE_MAP.get(code);
    }

    public static Boolean isBalanceInsufficientError(String errorCode) {
        return ACCOUNT_BALANCE_INSUFFICIENT.getCode().equals(errorCode) ||
               ACCOUNT_DW_BALANCE_INSUFFICIENT.getCode().equals(errorCode) ||
               ACCOUNT_TRANSFER_BALANCE_INSUFFICIENT.getCode().equals(errorCode) ||
               ACCOUNT_TRANSFER_FROM_SUBACCOUNT_INEXISTENT.getCode().equals(errorCode);
    }
}
