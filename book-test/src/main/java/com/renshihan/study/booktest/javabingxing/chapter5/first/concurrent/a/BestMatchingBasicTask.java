/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 18:43
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter5.first.concurrent.a;
//
// * 基于Callable

import com.renshihan.study.booktest.javabingxing.chapter5.first.common.BestMatchingData;
import com.renshihan.study.booktest.javabingxing.chapter5.first.common.LevenshteinDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 该类执行那些实现Callable接口并且将在执行器中执行的任务。
 * 每个任务处理一部分字典，并且返回这一部分字典获得的结果。
 *
 */
public class BestMatchingBasicTask implements Callable<BestMatchingData> {
    private int startIndex;
    private int endIndex;
    private List<String> dictionary;
    private String word;

    public BestMatchingBasicTask(int startIndex, int endIndex, List<String> dictionary, String word) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.dictionary = dictionary;
        this.word = word;
    }

    @Override
    public BestMatchingData call() throws Exception {
        List<String> result=new ArrayList<>();
        int minDistance=Integer.MAX_VALUE;
        int distance;
        for (int i = startIndex; i < endIndex; i++) {
            distance= LevenshteinDistance.calculate(word,dictionary.get(i));
            if(distance<minDistance){
                result.clear();
                minDistance=distance;
                result.add(dictionary.get(i));
            }else if(distance==minDistance){
                result.add(dictionary.get(i));
            }
        }
        return new BestMatchingData(result,minDistance);
    }

}