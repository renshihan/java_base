/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 18:28
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter5.first.serial;

import com.renshihan.study.booktest.javabingxing.chapter5.first.common.BestMatchingData;
import com.renshihan.study.booktest.javabingxing.chapter5.first.common.LevenshteinDistance;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于计算与输入字符串相似的单词的列表
 */
public class BestMatchingSerialCalculation {
    public static BestMatchingData getBestMatchingWords(String word, List<String> dictionary){
        List<String> results=new ArrayList<>();
        int minDistance=Integer.MAX_VALUE;
        int distance;
        for (String s : dictionary) {
            distance= LevenshteinDistance.calculate(word,s);
            if(distance<minDistance){
                results.clear();
                minDistance=distance;
                results.add(s);
            }else if(distance==minDistance){
                results.add(s);
            }
        }
        BestMatchingData bestMatchingData=new BestMatchingData();
        bestMatchingData.setDistance(minDistance);
        bestMatchingData.setWords(results);
        return bestMatchingData;
    }
}