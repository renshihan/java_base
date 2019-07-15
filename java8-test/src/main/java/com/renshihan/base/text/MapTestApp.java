/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/5/31 21:35
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.base.text;

import java.util.Date;
import java.util.Map;

/**
 * 测试map是否可以为参数输出
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/5/31 21:35
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2019/5/31 21:35
 */
public class MapTestApp {
    public static Map<String,Object> exec(Map<String,Object> map){
        map.put("abcd","aaaaaa");


        return map;
    }

    public static void main(String[] args) {
        System.out.println("date ---> "+ new Date());
    }
}