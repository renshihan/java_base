package com.renshihan.settlement.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@ApiModel
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubaccountQueryData {

    @ApiModelProperty(value = "币种", required = true)
    private String currency;

    @ApiModelProperty(value = "子账户类型", required = true, example = "trade")
    private String type;

    @ApiModelProperty(value = "余额", required = true, dataType = "string")
    private BigDecimal balance;

}
