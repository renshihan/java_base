/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/3/4 16:37
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.netty.api.impl;

import com.renshihan.study.netty.api.Person;
import com.renshihan.study.netty.api.PersonManage;

/**
 * ${TODO} 写点注释吧
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/3/4 16:37
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2019/3/4 16:37
 */
public class PersonManageImpl implements PersonManage {
    @Override
    public int save(Person person) {
        return 0;
    }

    @Override
    public void query(Person person) {

    }

    @Override
    public void check() {

    }

    @Override
    public boolean checkAge(Person person) {
        return false;
    }
}