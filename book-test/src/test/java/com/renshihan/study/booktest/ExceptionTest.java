/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/10/11 15:34
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * ${TODO} 写点注释吧
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/10/11 15:34
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2018/10/11 15:34
 */
public class ExceptionTest {
    public static void main(String[] args) {
        try {
            new ExceptionTest().methodA();
        } catch (Exception e) {
            StackTraceElement elements[] = e.getStackTrace();
            for (int i = 0; i < elements.length; i++) {
                StackTraceElement stackTraceElement = elements[i];
                String className = stackTraceElement.getClassName();
                String methodName = stackTraceElement.getMethodName();
                String fileName = stackTraceElement.getFileName();
                int lineNumber = stackTraceElement.getLineNumber();
                System.out.println("StackTraceElement数组下标 i=" + i + ",fileName="
                        + fileName + ",className=" + className + ",methodName=" + methodName + ",lineNumber=" + lineNumber);
            }
        }

    }

    private void methodA() {
        System.out.println("------进入methodA----------");
        methodB();
    }
    private void methodB() {
        System.out.println("------进入methodB----------");
//        StackTraceElement elements[] = Thread.currentThread().getStackTrace();
//        for (int i = 0; i < elements.length; i++) {
//            StackTraceElement stackTraceElement = elements[i];
//            String className = stackTraceElement.getClassName();
//            String methodName = stackTraceElement.getMethodName();
//            String fileName = stackTraceElement.getFileName();
//            int lineNumber = stackTraceElement.getLineNumber();
//            System.out.println("StackTraceElement数组下标 i=" + i + ",fileName="
//                    + fileName + ",className=" + className + ",methodName=" + methodName + ",lineNumber=" + lineNumber);
//        }
        methodC();
    }

    private void methodC() {
        System.out.println("------进入methodC----------");
        boolean isSuccess = true;
        if (isSuccess) {
            throw new RuntimeException("异常了");
        }
    }
}
