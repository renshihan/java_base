package com.renshihan.settlement.enums;

import lombok.Getter;

/**
 * OTC认证  实名等级 0-未实名，1-已实名，2-已高级
 */
@Getter
public enum OTCRealLevelEnum {
    NO_REAL_NAME(0),
    REAL_NAME(1),
    ADVANCED(2);
    private Integer level;
    OTCRealLevelEnum(Integer level){
        this.level = level;
    }
    public static boolean isAdvanced(Integer level){
        return OTCRealLevelEnum.ADVANCED.level.equals(level);
    }
}
