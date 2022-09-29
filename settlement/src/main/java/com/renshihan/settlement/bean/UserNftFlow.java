package com.renshihan.settlement.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.google.common.collect.ImmutableList;
import io.swagger.annotations.ApiModelProperty;
import jnr.ffi.annotations.In;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;
import org.web3j.abi.datatypes.Int;

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
public class UserNftFlow {

    @Excel(name = "id", orderNum = "1")
    @ApiModelProperty(name = "id" , value = "id")
    private Long id;

    @Excel(name = "from_user_id", orderNum = "2")
    @ApiModelProperty(name = "fromUserId" , value = "fromUserId")
    private Long fromUserId;

    @Excel(name = "to_user_id", orderNum = "3")
    @ApiModelProperty(name = "toUserId" , value = "toUserId")
    private String toUserId;


    @Excel(name = "nft_id", orderNum = "4")
    @ApiModelProperty(name = "nftId" , value = "nftId")
    private Long nftId;


    @Excel(name = "type", orderNum = "5")
    @ApiModelProperty(name = "type" , value = "type")
    private Integer type;

    @Excel(name = "currency", orderNum = "6")
    @ApiModelProperty(name = "currency" , value = "币种：小写")
    private String currency;

    @Excel(name = "price", orderNum = "7")
    @ApiModelProperty(name = "price" , value = "金额")
    private BigDecimal price;


    @Excel(name = "change_at", orderNum = "8")
    @ApiModelProperty(name = "changeAt" , value = "创建时间")
    private Long changeAt;

    @Excel(name = "created_at", orderNum = "9")
    @ApiModelProperty(name = "createdAt" , value = "创建时间")
    private Long createdAt;


    @Excel(name = "updated_at", orderNum = "10")
    @ApiModelProperty(name = "updatedAt" , value = "更新时间")
    private Long updatedAt;

    @Tolerate
    public UserNftFlow() {
    }

    public static List<String> getParams(){
        List<String> params= ImmutableList.of(
                "id",
                "fromUserId",
                "toUserId",
                "nftId",
                "type",
                "currency",
                "price",
                "changeAt",
                "createdAt",
                "updatedAt"
        );


        return params;
    }
}
