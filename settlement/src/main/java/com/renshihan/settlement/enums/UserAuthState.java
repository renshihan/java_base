package com.renshihan.settlement.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum  UserAuthState {

    UNKNOWN(0),

    VERIFIED(2),
    ;

    private final Integer value;

    public static UserAuthState of(Integer value) {
        return VERIFIED.value.equals(value) ? VERIFIED : UNKNOWN;
    }

    public boolean isVerified() {
        return this == VERIFIED;
    }
}
