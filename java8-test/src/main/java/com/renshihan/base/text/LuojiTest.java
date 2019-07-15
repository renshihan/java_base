/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/4/26 15:07
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.base.text;

/**
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/4/26 15:07
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2019/4/26 15:07
 */
public class LuojiTest {
    public static boolean exec() {
        return Boolean.TRUE;
    }

    public static boolean exec(String param) {
        if ("01".equals(param)) {
            return true;
        }


        return false;
    }

    public static void main(String[] args) {
        String a ="a";
        System.out.println(a.toLowerCase());
        System.out.println(a.toUpperCase());
    }
}