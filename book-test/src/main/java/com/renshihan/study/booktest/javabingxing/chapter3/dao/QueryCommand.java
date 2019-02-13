/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 16:34
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter3.dao;

/**
 * 查询命令
 */
public class QueryCommand extends Command{
    @Override
    public String execute() {
        if(command.length==3)
            return null;
                else{

        }
        return null;
    }

    public QueryCommand(String[] command) {
        super(command);
    }
}