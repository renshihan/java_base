package com.renshihan.settlement.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserAuthLevel {

    UNKNOWN(0),
    ORGANIZATION(9),
    PERSONAL(10),
    ;

    private final Integer value;

    public static UserAuthLevel of(int value) {
        switch (value) {
            case 9:
                return ORGANIZATION;
            case 10:
                return PERSONAL;
        }
        return UNKNOWN;
    }

    public boolean isPersonal() {
        return this == PERSONAL;
    }

    public boolean isOrganization() {
        return this == ORGANIZATION;
    }

    public boolean isUnknown() {
        return this == UNKNOWN;
    }
}
