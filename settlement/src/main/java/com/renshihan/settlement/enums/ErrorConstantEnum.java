package com.renshihan.settlement.enums;

import cn.gjing.tools.common.util.ParamUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

/**
 * 错误码枚举
 * 2xxxx 业务错误
 * 9xxxx 系统错误
 */
public enum ErrorConstantEnum {


    INCOMING_PARAMETER_EXCEPTION_SETTLEMENT(30001, "传入参数异常"),
    USER_LOGIN_ACCOUNT_IS_NULL(30016, "用户账户不存在"),
    GET_USER_INFO_MSG_FAILSE(30017, "获取用户信息失败"),


    USER_MAIN_ACCOUNT_ERROR(40001, "用户未登录母账号"),
    USER_KYC_CHECK_ERROR(40002, "用户kyc认证未通过"),
    USER_TOKEN_CHECK_ERROR(40003, "用户token校验不通过"),
    ORDER_IS_NULL(40004, "未找到订单信息"),
    STOP_REDEEM_SERVICE(40005, "赎回暂不支持"),
    DO_NOT_CLICK_REPEATEDLY(40006, "请勿重复点击"),
    USER_NOT_MATCH(40007, "用户不匹配"),
    USER_NOT_ORDER_MATCH(40008, "用户订单不匹配"),
    USER_QUOTA_NOT_ENOUGH(40009, "用户申购额度不足"),
    ORDER_CREATE_FAILURE(40010, "下单失败"),
    MAIN_ACCOUNT_NOT_SUPPORT(40011, "非母账号不支持挖矿宝业务"),
    TIME_NOT_SUPPORT_REDEEM(40012, "当前时段不支持赎回"),
    USER_LOGIN_STATUS_TIMEOUT(40013, "用户登陆已过期"),

    CURRENCY_2CURRENCY_MISS(50001, "金额折算币种对缺失"),
    USER_TYP_IS_NULL(50005, "用户类型缺失"),
    ORDER_NOT_FIXED(50006, "订单非定期"),


    PROJECT_NOT_EXIST(60002,"项目不存在"),
    PROJECT_NOT_USE(60003,"无可用项目"),
    PROJECT_NOT_VIEW_USE(60004,"无可展示项目"),
    SAVINGS_PRODUCT_IS_NOT_EXISTS_ERROR(60052,"无效的项目"),
    SAVINGS_BALANCE_NOT_ENOUGH(60054,"可赎额度不足"),
    SAVINGS_CONVERT_ORDER_ERROR(60058,"无效订单"),
    SAVINGS_TOTAL_SELL_INVENTORY_UPPER_EXCEED(60062,"项目额度不足"),
    SAVINGS_PROJECT_NOT_IN_SELL_WINDOW_PERIOD_ERROR(60064,"不在售卖窗口期"),
    SAVINGS_USER_BUY_UPPER_EXCEED(60065,"超过可用余额"),
    SAVINGS_LESS_THAN_SMALLEST_INVESTMENT_ERROR(60067,"小于最小起投金额"),
    SAVINGS_ORDER_NOT_EXIST(60201,"订单不存在"),
    BALANCE_AUTO_DIGGING_ERROR(60206,"灵活自动挖矿标识不在合法值内"),
    PROJECT_IS_END_ERROR(60207,"当前项目已经结束"),
    PROJECT_IS_FULL_ERROR(60208,"当前项目已经募满"),
    PROJECT_IS_NOT_START_ERROR(60209,"当前项目处理于募资准备中"),
    REDEEM_NOT_SUPPORT_FIXED(60701,"定期不支持赎回"),
    REDEEM_NOT_SAME_USER_ACCOUNT(60702,"赎回的用户不是同一帐户"),
    SPOT_TO_SYSTEM_CLCT_ERROR(60800,"扣款失败"),
    REDEEM_AMOUNT_GREATER_THAN_0(60803,"赎回金额必须大于0"),
    SYSTEM_EXPEND_TO_SPOT_WAITING(60804,"系统正在处理,资产将一分钟后到账"),
    ACTIVITY_IS_NOT_START(60805,"活动尚未开启"),
    NOT_HAVE_POSITION_RIGHTS(60806,"尚未获得%s持仓权益"),

    REQUEST_FREQUENTLY(501, "请求频繁"),
    FAILURE(500,"系统繁忙，请稍后再试"),
    SUCCESS(200, "请求成功"),
    REQUEST_PARAM_EXCEPTION(90000, "请求参数异常"),
    SIGN_VERIFY_FAILED(90001, "验签失败"),
    AUTHORIZATION_EXCEPTION(99997, "没有权限，请联系管理员授权"),
    DUPLICATEKEY_EXCEPTION(99998, "数据库中已存在该记录"),
    SYSTEM_EXCEPTION(99999, "未知异常");


    private Integer errCode;
    private String errMsg;
    private Predicate predicate;


    ErrorConstantEnum(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    ErrorConstantEnum(Integer errCode, String errMsg, Predicate predicate) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.predicate = predicate;

    }

    public Integer getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public boolean verify(Object object) {
        return predicate.test(object);
    }

    public boolean isSuccess() {
        return ErrorConstantEnum.SUCCESS.getErrCode().equals(this.getErrCode());
    }

    public static ErrorConstantEnum getErrorByMsg(String msg, ErrorConstantEnum defaultErrorEnum) {
        if (StringUtils.isEmpty(msg)) {
            return defaultErrorEnum;
        }
        for (ErrorConstantEnum errorConstantEnum : ErrorConstantEnum.values()) {
            if (errorConstantEnum.getErrMsg().contains(msg)) {
                return errorConstantEnum;
            }
        }
        return defaultErrorEnum;
    }

    @Override
    public String toString() {
        return "ErrorConstantEnum{" + "errCode=" + errCode + ", errMsg='" + errMsg + '\'' + '}';
    }

    public static String info(ErrorConstantEnum errorConstantEnum) {
        return info(null, errorConstantEnum, null);
    }


    public static String info(ErrorConstantEnum errorConstantEnum, Object request) {
        return info(null, errorConstantEnum, request);
    }

    public static String info(String prefix, ErrorConstantEnum errorConstantEnum, Object request) {
        StringBuffer info = new StringBuffer();
        if (ParamUtil.isNotEmpty(prefix)) {
            info.append(prefix).append(":");
        }
        if (null != errorConstantEnum) {
            info.append("errorCode=").append(errorConstantEnum.getErrCode()).append(",errorMessage=").append(errorConstantEnum.getErrMsg()).append(";");
        }
        if (ParamUtil.isNotEmpty(request)) {
            info.append("request=").append(JSONObject.toJSONString(request));
        }
        return info.toString();
    }

    public static ErrorConstantEnum getByCode(Integer code){
        for (ErrorConstantEnum value : ErrorConstantEnum.values()) {
            if(value.getErrCode().equals(code)){
                return value;
            }
        }
        return null;
    }
}
