/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/10/12 16:01
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.base.optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * optional练习
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/10/12 16:01
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2018/10/12 16:01
 */
public class OptionalTest1 {
    public static void main(String[] args) {
        Map<String,String> abcMap=new HashMap<>();
        abcMap.put("aaa","bbb");
        abcMap.put("aaa1","bbb");
        abcMap.put("aaa2","bbb");
        abcMap.put("aaa2",null);
        abcMap.forEach((k,v)->{
            System.out.println("key="+k+",value="+Optional.ofNullable(v).orElse("空了"));
        });
    }
}