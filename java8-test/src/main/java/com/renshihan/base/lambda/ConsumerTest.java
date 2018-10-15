/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/10/15 16:01
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.base.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Consumer
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/10/15 16:01
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2018/10/15 16:01
 */
public class ConsumerTest {
    public static void main(String[] args) {
        List names = new ArrayList();
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        forEarch(names, (String name) -> System.out.println(name));

    }

    public static <T> void forEarch(List<T> list, Consumer<T> consumer) {
        for (T i : list) {
            consumer.accept(i);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t :
                list) {
            result.add(function.apply(t));
        }
        return result;
    }

}