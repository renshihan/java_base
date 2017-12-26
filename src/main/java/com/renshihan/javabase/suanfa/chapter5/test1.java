package com.renshihan.javabase.suanfa.chapter5;

/**
 * Created by admin on 2017/8/29.
 * 字符串的统计字符串
 * 题目：给定一个字符串str,返回str的统计字符串，例如: 'aaabbadddffc'的统计字符串为'a_3_b_2_a_1_d_3_f_2_c_1'
 */
public class test1 {
    public static String result(String str){
        if (str ==null || str.equals("")){
            return "";
        }
        char[] chs=str.toCharArray();
        String res= String.valueOf(chs[0]); //表示统计字符串
        int num = 1; //标识当前字符的数量
        /* 从str[1]位置开始，从左到右遍历str */
        for (int i =1;i <chs.length; i++) {
            //如果str[i] != str[i-1] ，则表示连续出现的字符[str[i-1]]已经结束,
            if (chs[i] != chs[i -1]){
                res=concat(res,String.valueOf(num),String.valueOf(chs[i]));
                System.out.println("---------res:"+res);
                num= 1;
            }else {
                //如果str[i] == str[i-1] 则说明当前连续出现的字符（str[i-1]）还没结束，令num++
                num ++;
                System.out.println("------------res--------"+res);
            }
        }
        return concat(res,String.valueOf(num),"");
    }
    private static String concat (String s1,String s2,String s3){
        return  String.format("%s_%s%s",s1,s2,
                s3.equals("") ? s3 :("_"+s3));
    }

    public static void main(String[] args) {
        String a="aaabbbccdddffffffffff";
        System.out.println(test1.result(a));
    }
}
