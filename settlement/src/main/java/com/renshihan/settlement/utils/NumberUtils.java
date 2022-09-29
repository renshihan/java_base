package com.renshihan.settlement.utils;

import java.util.Objects;

/**
 * 数值类工具
 *
 * @author simon.chi
 */
public class NumberUtils {

    public static boolean gtZero(Integer number) {
        return number != null && number > 0;
    }

    public static boolean gtEqZero(Long num) {
        return Objects.nonNull(num) && num >= 0;
    }

    public static boolean gtZero(Long num) {
        return Objects.nonNull(num) && num > 0;
    }

    public static boolean equalsZero(Long number) {
        return Objects.nonNull(number) && number == 0;
    }

    public static boolean ltEqualsZero(Integer number) {
        return Objects.nonNull(number) && number <= 0;
    }

}
