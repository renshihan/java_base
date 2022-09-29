package com.renshihan.settlement.utils;

import org.apache.commons.lang3.StringUtils;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public final class NumericUtil {

    /**
     * 对double类型的数值保留指定位数的小数。 该方法舍入模式：向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。
     *
     * @param number    要保留小数的数字
     * @param precision 小数位数
     * @return double 如果数值较大，则使用科学计数法表示
     */
    public static double keepPrecision(double number, int precision) {
        return keepPrecision(number, precision, RoundingMode.HALF_UP);
    }

    public static double keepPrecision(double number, int precision, RoundingMode roundingMode) {
        BigDecimal bg = BigDecimal.valueOf(number);
        return bg.setScale(precision, roundingMode).doubleValue();
    }

    /**
     * @param v
     * @param decimals
     * @return
     */
    public static double uint256WithDecimals(Uint256 v, int decimals) {
        return numberWithDecimals(v.getValue(), decimals);
    }

    /**
     * @param v
     * @param decimals
     * @return
     */
    public static double numberWithDecimals(BigInteger v, int decimals) {
        if(v==null){
            return 0;
        }
        BigDecimal bd = new BigDecimal("10");
        bd = bd.pow(decimals);
        BigDecimal ret = new BigDecimal(v).divide(bd, decimals, BigDecimal.ROUND_DOWN);
        return ret.doubleValue();
    }

    /**
     * n for decimals
     *
     * @param decimals
     * @return
     */
    public static BigInteger n(int n, int decimals) {
        return BigInteger.valueOf(n).multiply(one(decimals));
    }

    public static double safeParseNumber(String amount) {
        if (StringUtils.isBlank(amount)) {
            return 0;
        }
        try {
            return Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static double safeParseNumber(BigDecimal amount) {
        if (amount == null) {
            return 0;
        }
        return amount.doubleValue();
    }


    /**
     * one for decimals
     *
     * @param decimals
     * @return
     */
    public static BigInteger one(int decimals) {
        BigInteger bi = BigInteger.valueOf(10);
        return bi.pow(decimals);
    }

}
