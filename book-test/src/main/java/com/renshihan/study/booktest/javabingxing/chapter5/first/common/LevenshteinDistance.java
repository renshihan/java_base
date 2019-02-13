/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 18:07
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter5.first.common;

/**
 * 用于计算两个字符串之间的Levenshtein距离
 */
public class LevenshteinDistance {
    /**
     * Levenshtein距离，是指将第一个字符串转换成第二个
     * 字符串所需进行的最少的插入行,删除或替换操作次数。
     * @param string1
     * @param string2
     * @return
     */
    public static int calculate(String string1,String string2){
        int[][] distances=new int[string1.length()+1][string2.length()+1];
        for (int i = 0; i <= string1.length(); i++) {
            distances[i][0]=i;
        }
        for (int j = 0; j <= string2.length(); j++) {
            distances[0][j]=j;
        }
        for (int i = 0; i < string1.length(); i++) {
            for (int j = 0; j < string2.length(); j++) {
                if(string1.charAt(i-1)==string2.charAt(j-1)){
                    distances[i][j]=distances[i-1][j-1];
                }else {
                    distances[i][j]=minimum(
                            distances[i-1][j],
                            distances[i][j-1],
                            distances[i-1][j-1]
                    )+1;
                }
            }
        }
        return distances[string1.length()][string2.length()];
    }
    private static int minimum(int i,int j,int k){
        return Math.min(i,Math.min(j,k));
    }
}