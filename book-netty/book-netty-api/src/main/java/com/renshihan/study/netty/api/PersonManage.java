/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/3/4 15:28
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.netty.api;

/**
 *
 */
public interface PersonManage {
    int save(Person person);
    void query(Person person);
    void check();
    boolean checkAge(Person person);
}