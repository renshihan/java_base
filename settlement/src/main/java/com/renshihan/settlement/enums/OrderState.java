package com.renshihan.settlement.enums;

import com.google.common.base.CaseFormat;
import lombok.Getter;

import java.util.Map;

@Getter
public enum OrderState {

    UNKNOWN(0),

    SUBMITTED(3),
    PARTIAL_FILLED(4),
    PARTIAL_CANCELED(5),
    FILLED(6),
    CANCELED(7),
    ;


    private final Integer value;

    private final String name;

    private final boolean finished;

    OrderState(Integer value) {
        this.value = value;
        this.name = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());
        this.finished = value >= 5 && value <= 7;
    }

}
