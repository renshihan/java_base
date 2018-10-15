/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/10/15 13:53
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.base.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 谓语 predicate用法
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/10/15 13:53
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2018/10/15 13:53
 */
public class LambdaTest2 {
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> results = new ArrayList<>();
        for (T s : list
                ) {
            if (predicate.test(s)) {
                results.add(s);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        Predicate<String> nonEmptyStringPredicate=(String s) ->!s.isEmpty();
//        List<String> a=
//        List<Integer> demo=filter()

    }

}