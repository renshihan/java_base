package com.renshihan.settlement.enums;

import lombok.Getter;

/**
 * 订单处理结果
 */
@Getter
public enum OrderProcessResult {
    PROCESS_SUCCESS(1),
    PROCESSING(2),
    ;
    private Integer result;

    OrderProcessResult(Integer result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return this == PROCESS_SUCCESS;
    }
}
