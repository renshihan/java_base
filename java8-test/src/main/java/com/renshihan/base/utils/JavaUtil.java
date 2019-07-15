/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/12/4 13:51
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.base.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * ${TODO} 写点注释吧
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/12/4 13:51
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2018/12/4 13:51
 */
public class JavaUtil {
    public static void main(String[] args) {
        List<String> rows=new ArrayList<String>(10);
        for (int i = 0; i < 10; i++) {
            System.out.println("------"+i);
            rows.add(i,i+1+"");
        }
        int end=rows.size()/3;
        System.out.println("end="+end);
        for (int i = 0; i <=(rows.size()/3); i++) {
            System.out.println("调用次数----"+i);
        }
    }
}