/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 18:08
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter5.first.common;

import lombok.Data;

import java.util.List;

/**
 * 用于存放最佳匹配算法的结果。它存储了单词列表以及这些单词
 * 与输入字符串之间的距离。
 */
@Data
public class BestMatchingData {
    private List<String> words;
    private int distance;

    public BestMatchingData(List<String> words, int distance) {
        this.words = words;
        this.distance = distance;
    }
}