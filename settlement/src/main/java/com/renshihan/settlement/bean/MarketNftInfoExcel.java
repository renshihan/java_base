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
@Accessors(chain = true)
public class MarketNftInfoExcel {

    @Excel(name = "id", orderNum = "1")
    private Long id;

    @Excel(name = "token_id", orderNum = "2")
    private String token_id;

    @Excel(name = "future_address", orderNum = "3")
    private String future_address;

    @Excel(name = "network", orderNum = "4")
    private String network;

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
