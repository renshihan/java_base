/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 17:08
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter3;


import javassist.tools.rmi.Sample;

import java.util.List;

/**
 * 实现K-最近邻算法的串行版本
 * 该类内存储了训练数据集和数值k（用于确定某个实例标签的范例数量）
 */
public class KnnClassifier {
    private final List<?extends Sample> dataSet;
    private int k;

    public KnnClassifier(List<? extends Sample> dataSet,int k) {
        this.dataSet = dataSet;
        this.k=k;
    }
    public String classif(Sample example){

        return null;
    }
}