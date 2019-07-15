/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/5/6 18:36
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.base.text;

/**
 * 逻辑判断
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/5/6 18:36
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2019/5/6 18:36
 */
public class Test123 {
    public static boolean exec(String token1, String token2) {
        if (null == token1 || token1.length() == 0) {
            return false;
        }
        if (null == token2 || token2.length() == 0) {
            return false;
        }
        if (token1.equals(token2)) {
            return true;
        }

        return false;
    }
}