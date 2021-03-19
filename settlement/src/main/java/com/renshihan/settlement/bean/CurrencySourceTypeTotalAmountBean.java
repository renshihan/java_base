package com.renshihan.settlement.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencySourceTypeTotalAmountBean {
    @Excel(name = "currency", orderNum = "1")
    @ApiModelProperty(name = "currency" , value = "币种")
    private String currency;
    @Excel(name = "sourceType", orderNum = "2")
    @ApiModelProperty(name = "sourceType" , value = "来源类型")
    private Integer sourceType;

    @Excel(name = "side", orderNum = "3")
    @ApiModelProperty(name = "side" , value = "出入方向")
    private String side;
    @Excel(name = "productType", orderNum = "4")
    @ApiModelProperty(name = "productType" , value = "产品类型")
    private Integer productType;
    @Excel(name = "totalAmount", orderNum = "5")
    @ApiModelProperty(name = "totalAmount" , value = "总金额")
    private BigDecimal totalAmount;
}
