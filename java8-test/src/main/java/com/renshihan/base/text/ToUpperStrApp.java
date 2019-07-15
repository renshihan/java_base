/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/5/31 10:38
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.base.text;

/**
 * string小写转大写
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/5/31 10:38
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2019/5/31 10:38
 */
public class ToUpperStrApp {
    public static String exec(String string){
        if(null==string||string.length()==0){
            return string;
        }
        return string.toUpperCase();
    }
}