package com.renshihan.settlement.utils;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

@Slf4j
public class AmountUtil {
    public static DecimalFormat amount8Format = new DecimalFormat("0.00000000");
    public static DecimalFormat amount4Format = new DecimalFormat("0.0000");
    public static DecimalFormat amount3Format = new DecimalFormat("0.000");
    public static DecimalFormat amount2Format = new DecimalFormat("0.00");
    public static DecimalFormat amount0Format = new DecimalFormat("0");
    private static BigDecimal decimal01 = new BigDecimal(0.1);
    private static BigDecimal decimal001 = new BigDecimal(0.01);
    private static BigDecimal ten = new BigDecimal("10");
    private static MathContext math3=new MathContext(3);
    public static String view(Object amount) {
        if (null == amount) {
            return BigDecimal.ZERO.toPlainString();
        }
        BigDecimal bAmount = null;
        if (amount instanceof Long) {
            bAmount = new BigDecimal((Long) amount);
        } else if (amount instanceof Integer) {
            bAmount = new BigDecimal((Integer) amount);
        } else if (amount instanceof BigDecimal) {
            bAmount = (BigDecimal) amount;
        } else {
            log.info("传入amount类型不支持--->{}", JSONObject.toJSONString(amount));
            throw new RuntimeException("传入amount类型不支持");
        }
//        return rvZeroAndDot(bAmount.divide(BigDecimal.valueOf(Math.pow(10, 8))).toPlainString());
        return rvZeroAndDot(amount2String(bAmount.longValue(), 8));
    }

    public static String rvZeroAndDot(String s) {
        if (s.isEmpty()) {
            return null;
        }
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    public static String rvZeroAndDot(BigDecimal bigDecimal) {
        if (null == bigDecimal) {
            return null;
        }
        if (BigDecimal.ZERO.equals(bigDecimal)) {
            return "0";
        }
        return rvZeroAndDot(bigDecimal.toPlainString());
    }


    public static String convertPoint8(BigDecimal bigDecimal) {
        if (null == bigDecimal) {
            bigDecimal = BigDecimal.ZERO;
        }
        return amount8Format.format(bigDecimal);
    }

    public static String convertDefalut(BigDecimal amount){
        if(amount.compareTo(decimal01)>=0){
            return convert(amount,2);
        }
        if(amount.compareTo(decimal001)>=0){
            return convert(amount,4);
        }
        //有效数字
        return amount.divide(BigDecimal.ONE,math3).toPlainString();

    }
    public static String convert(BigDecimal amount, Integer precision) {

        if (null == precision) {
            //默认8位
            return amount8Format.format(amount);
        }
        if (precision.equals(2)) {
            return amount2Format.format(amount);
        }
        if (precision.equals(3)) {
            return amount3Format.format(amount);
        }
        if (precision.equals(4)) {
            return amount4Format.format(amount);
        }
        if (precision.equals(8)) {
            return amount8Format.format(amount);
        }
        if(precision.equals(0)){
            return amount0Format.format(amount);
        }
        String formatStr = "0.";
        for (Integer i = 0; i < precision; i++) {
            formatStr += "0";
        }
        return new DecimalFormat(formatStr).format(amount);
    }




    public static String amount2String(long amount, int decimal) {
        String amountString = String.valueOf(amount);
        int leftZeroCount = decimal - amountString.length();
        StringBuilder result = new StringBuilder();
        if (leftZeroCount < 0) {
            result.append(amountString.substring(0, amountString.length() - decimal))
                    .append(".").append(amountString.substring(amountString.length() - decimal));
        } else {
            result.append("0.");
            for (int j = 0; j < leftZeroCount; j++) {
                result.append("0");
            }
            result.append(amountString);
        }
        return result.toString();
    }



}
