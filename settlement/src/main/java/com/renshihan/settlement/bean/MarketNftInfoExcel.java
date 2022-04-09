package com.renshihan.settlement.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.google.common.collect.ImmutableList;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ming.jia
 * @version 1.0
 * @description 订单流水表
 * @date 2021/1/7 17:56
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
public class MarketNftInfoExcel {

    @Excel(name = "f_id", orderNum = "1")
    @ApiModelProperty(name = "id" , value = "id")
    private Long id;

    @Excel(name = "f_from_user_id", orderNum = "7")
    @ApiModelProperty(name = "fromUserId" , value = "fromUserId")
    private Long fromUserId;

    @Excel(name = "f_to_user_id", orderNum = "9")
    @ApiModelProperty(name = "toUserId" , value = "toUserId")
    private Long toUserId;

    @Excel(name = "f_currency", orderNum = "11")
    @ApiModelProperty(name = "currency" , value = "币种：小写")
    private String currency;

    @Excel(name = "f_amount", orderNum = "12")
    @ApiModelProperty(name = "amount" , value = "划转金额")
    private BigDecimal amount;

    @Excel(name = "f_created_at", orderNum = "15")
    @ApiModelProperty(name = "createdAt" , value = "创建时间")
    private Long createdAt;

    @Excel(name = "f_type", orderNum = "15")
    @ApiModelProperty(name = "f_type" , value = "类型")
    private Integer type;

    @Excel(name = "f_transaction_id", orderNum = "15")
    @ApiModelProperty(name = "transactionId" , value = "创建时间")
    private Long transactionId;
    @Tolerate
    public MarketNftInfoExcel() {
    }

    public static List<String> getParams(){
        List<String> params= ImmutableList.of(
                "id",
                "token_id",
                "future_address",
                "network"
        );


        return params;
    }
}
