/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhang_ze[zhang_ze@suixingpay.com]
 * @date: 2018/8/9 18:48
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.base.引入流;

import java.util.UUID;

/**
 * id 生成器
 *
 * @author: zhang_ze[zhang_ze@suixingpay.com]
 * @date: 2018/8/9 18:48
 * @version: V1.0
 * @review: zhang_ze[zhang_ze@suixingpay.com]/2018/8/9 18:48
 */
public class IdGenerator {

    private IdGenerator() {
    }

    /**
     * 生成32位UUID
     *
     * @return UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 向前补0
     *
     * @param suffix 原始字符串
     * @param length 补齐之后的长度
     */
    public static String addZeroToStart(String suffix, int length) {
        if (length <= suffix.length()) {
            return suffix;
        }
        int end = length - suffix.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < end; i++) {
            result.append("0");
        }
        result.append(suffix);
        return result.toString();
    }
    
    /**
     *<pre>
     *     format字符串
     *
     * @param suffix 前缀
     * @param length 结果总长度
     * @return result
     *</pre>
     */
    public static String padZeroToStart(String suffix, int length) {
        
        if (length <= suffix.length()) {
            return suffix;
        }
        
        return String.format("%0" + length + "d", Integer.valueOf(suffix));
    }

   public static void main(String[] args) {
       /* for(int i=0;i<20;i++){
            System.out.println(getUUID());
        }*/

       String test="abc/efg/hij";
       String[] array= test.split("\\/");
       System.out.print(array[array.length-1]);
    }
}
