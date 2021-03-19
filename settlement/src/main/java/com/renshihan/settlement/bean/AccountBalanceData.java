package com.renshihan.settlement.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 查询指定账户的余额
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalanceData {
    /**
     * 账户id，传入的account-id
     */
    private Long id;
    /**
     * 账户类型，详细分类，主账户类型
     */
    private String type;
    /**
     * 账户状态，详细状态，主账户状态
     */
    private String state;
    /**
     * 账户子类型: 如果是C2C借款账户，值就是symbol。如果是全仓或是C2C出借账户，其值是exchangeId
     */
    @JsonProperty(value = "subtype")
    private String subType;
    /**
     * 子账户列表
     */
    private List<SubaccountQueryData> list;
}
