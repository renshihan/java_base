package com.renshihan.settlement.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author zhengsiyu
 * @description:
 * @email zhengsiyu@huobi.com
 * @date 15:22 2020-07-21
 */
public enum AccountState {

    /**
     * 未知
     */
    UNKNOWN("unknown"),

    /**
     * 正常
     */
    WORKING("working"),

    /**
     * 锁定
     */
    LOCK("lock"),

    /**
     * 系统自动爆仓
     */
    FL_SYS("fl-sys"),

    /**
     * MGT手动爆仓
     */
    FL_MGT("fl-mgt"),

    /**
     * 爆仓结束
     */
    FL_END("fl-end"),

    /**
     * 穿仓
     */
    FL_NEGATIVE("fl-negative"),

    ;

    @Getter
    private String state;

    private AccountState(String state) {
        this.state = state;
    }

    public static AccountState getByState(String state) {
        return Arrays.stream(AccountState.values()).filter(e -> e.state.equals(state)).findFirst().orElse(UNKNOWN);
    }

    /**
     * 账户是否正常
     *
     * @return
     */
    public boolean isWorking() {
        return this == WORKING;
    }
}
