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
public class SavingOrderRecordPo {

    private static final long serialVersionUID = -1263693354805779225L;


    @Excel(name = "f_id", orderNum = "1")
    @ApiModelProperty(value = "id")
    private Long id;

    @Excel(name = "f_user_id", orderNum = "2")
    @ApiModelProperty(value = "用户Id")
    private Long userId;

    @Excel(name = "f_source_type", orderNum = "3")
    @ApiModelProperty(name = "sourceType", value = "来源类型：0-主动（用户主动下单-手动挖矿）；1-自动定转活；2-余额自动挖矿;3-到期赎回;4-随时赎回;" +
            "5-收益发放;6-收益复投；7-存币宝升级转入;8-测试插入（方便删除）")
    private Integer sourceType;

    @Excel(name = "f_currency", orderNum = "4")
    @ApiModelProperty(value = "币种")
    private String currency;

    @Excel(name = "f_amount", orderNum = "5")
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @Excel(name = "f_create_time", orderNum = "6")
    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @Tolerate
    public SavingOrderRecordPo() {
    }

    public static List<String> getParams(){
        List<String> params= ImmutableList.of(
                "id",
                "userId",
                "sourceType",
                "currency",
                "amount",
                "createTime"
        );


        return params;
    }

}
