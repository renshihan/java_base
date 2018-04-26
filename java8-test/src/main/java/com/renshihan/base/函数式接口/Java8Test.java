package com.renshihan.base.函数式接口;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * @author renshihan@winchannel.net
 * @date 2018/4/26 14:40
 *
 */
public class Java8Test {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Map<String,Integer> map=new HashMap<>();
        map.put("111",111);
        map.put("222",222);
        //1.返回boolean
        Predicate<Integer> predicate1=n->true;
        Predicate<Integer> predicate2=n->n%2==0;
        Predicate<Integer> predicate3=n->n>3;

        System.out.println("输出所有参数..");
        eval(list,predicate1);
        System.out.println("输出所有偶数");
        eval(list,predicate2);
        System.out.println("输出所有大于3的数字");
        eval(list,predicate3);

        list.spliterator().trySplit();

        //2.
        BiConsumer biConsumer=(k,v)-> System.out.println("key--->"+k+",value:"+v);
        bic(map,biConsumer);
    }
    //函数式接口
    // Predicate<Integer> predicate = n -> true
    // n 是一个参数传递到 Predicate 接口的 test 方法
    // n 如果存在则 test 方法返回 true
    public static void eval(List<Integer> list, Predicate<Integer> predicate){
        list.forEach(num->{
            if(predicate.test(num)){
                System.out.println(num+" ");
            }
        });
    }
    //	BiConsumer<T,U> 代表了一个接受两个输入参数的操作，并且不返回任何结果
    public static void bic(Map<String,Integer> map, BiConsumer<String, Integer> biConsumer){
        map.forEach(biConsumer);
    }
}
