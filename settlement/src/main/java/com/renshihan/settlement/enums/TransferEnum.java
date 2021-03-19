package com.renshihan.settlement.enums;

import lombok.Getter;

/**
 * @author ming.jia
 * @version 1.0
 * @description 划转状态
 * @date 2020/12/21 13:58
 **/
public interface TransferEnum {


    /**
     * 划转请求来源
     */
    enum TransferRequest{
        REQUEST_SOURCE_DB("db", "定期本金"),
        REQUEST_SOURCE_DL("dl", "定期利息"),
        REQUEST_SOURCE_HL("hl", "活期利息"),
        REQUEST_SOURCE_HS("hs", "活期赎回"),
        REQUEST_SOURCE_DS("ds", "定期赎回"),
        REQUEST_SOURCE_SG("sg", "申购产品"),
        ;

        @Getter
        private final String value;

        @Getter
        private final String desc;

        TransferRequest(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }
    /**
     * 划转状态
     */
    enum TransferStatus {
        TRANSFER_WAITING(0, "待划转"),
        TRANSFER_OK(1, "划转成功"),
        TRANSFER_ERROR(2, "划转失败"),
        TRANSFER_NEED_RETRY(3, "划转失败待重试"),
        ;

        @Getter
        private final Integer value;

        @Getter
        private final String desc;

        TransferStatus(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }


    /**
     * 挖矿宝:划转类型
     */
    enum TransferType {
        POOL_SAVINGS_SPOT_TO_CLCT("pool-savings-spot-to-clct","用户现货币币 -> 资金归集"),
        POOL_SAVINGS_CLCT_TO_EXPEND("pool-savings-clct-to-expend","资金归集 -> 资金支出"),
        POOL_SAVINGS_EXPEND_TO_SPOT("pool-savings-expend-to-spot","资金支出 -> 用户现货币币(本金清算)"),
        POOL_SAVINGS_INTEREST_TO_SPOT("pool-savings-interest-to-spot","利息账户 -> 用户现货币币"),
        POOL_SAVINGS_INTEREST_TO_EXPEND("pool-savings-interest-to-expend","利息账户 -> 资金支出(活期利息复投)"),
        CASH_CONCENTRATION("cash-concentration","利息账户 ->  收入归集账户"),
        CONCENTRATION_TO_OPERATION("concentration-to-operation","收入归集账户 -> 运营账户"),
        POOL_SAVINGS_OPS_TO_ASSET_MANAGEMENT_SPOT("pool-savings-ops-to-asset-management-spot","运营账户 -> 量化财金专用现货币币"),
        POOL_SAVINGS_ASSET_MANAGEMENT_SPOT_TO_OPS("pool-savings-asset-management-spot-to-ops","量化财金专用现货币币 -> 运营账户"),
        POOL_SAVINGS_ASSET_MANAGEMENT_SPOT_TO_INTEREST("pool-savings-asset-management-spot-to-interest","量化财金专用现货币币 -> 利息账户(利息划转)"),
        ;

        @Getter
        private final String value;

        @Getter
        private final String desc;

        TransferType(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }


    /**
     * 挖矿宝:划转请求服务类型
     */
    enum TransferRequestType {
        POOL_SAVINGS_ORDER("order", "订单服务"),
        POOL_SAVINGS_CLEAR("clear", "清算服务"),
        ;

        @Getter
        private final String value;

        @Getter
        private final String desc;

        TransferRequestType(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }
}
