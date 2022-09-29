package com.renshihan.settlement.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MetadataCode implements BaseCode {
    CONFIG_NODE_ERROR(60001,"web3j 配置节点url错误"),
    NFT_INFO_DATA_IS_NULL_ERROR(60002,"保存metadata数据时,nftInfoData不能为空"),
    NO_ACCESS(60003,"没有权限访问"),
    SYSTEM_ERROR(69999,"系统错误"),
    SUCCESS(200,"成功"),
    ;

    private Integer code;
    private String desc;
}
