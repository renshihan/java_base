package com.renshihan.settlement.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Random;

public class CommonUtils {

    /**
     * big decimal convert to string
     * @param val
     * @return
     */
    public static String convert(BigDecimal val,int scale){
        if(Objects.isNull(val)){
            return "";
        }
      return   val.setScale(scale, RoundingMode.DOWN).toPlainString();
    }

    public static String getRandomNum(int randomNum) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= randomNum; i++) {
            Random random = new Random();
            stringBuilder.append(random.nextInt(9));
        }
        return stringBuilder.toString();
    }
    /**
     * 范围内随机数字
     *
     * @param from 开始
     * @param to   结束
     * @return int int
     */
    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }
}
