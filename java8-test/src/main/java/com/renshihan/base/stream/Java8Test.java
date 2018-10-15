package com.renshihan.base.stream;

import com.renshihan.commons.util.DateHelper;
import com.renshihan.commons.util.StringHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author renshihan@winchannel.net
 * @date 2018/4/26 16:21
 * 参考博客:https://blog.csdn.net/u010425776/article/details/52344425
 */
@Slf4j
public class Java8Test {
    private static final List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl", "", "abc");
    private static final List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
    private static final List<Integer> numbers2 = Arrays.asList( 2, 3, 7, 3, 5);

    public static void main(String[] args) {
        System.out.println("---->"+numbers.containsAll(numbers2));
        //
        Random random = new Random();

//        System.out.println("java-8...");
//
//
//
//
//        System.out.println("square list：" + numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList()));
//
//        System.out.println("------------------------------------------------------------------");
//        IntSummaryStatistics starts=numbers.stream().mapToInt(x ->x).summaryStatistics();
//        System.out.println("列表中最大的数:"+starts.getMax());
//        System.out.println("列表中最小的数:"+starts.getMin());
//        System.out.println("所有数之和："+starts.getSum());
//        System.out.println("平均数："+starts.getAverage());
//        random.ints().limit(10).sorted().forEach(System.out::println);
//        System.out.println("随机数:"+"");
//        System.out.println("并行处理:"+stringList.parallelStream().filter(string->string.isEmpty()).count());
//        System.out.println("去掉重复："+);

    }

    /**
     * @return
     * @author: renshihan@winchannel.net
     * @Description: 筛选filter
     * @Date 2018/4/28 13:55
     * @Param
     */
    @Test
    public void test1() {
        log.info("空字符串数量:{}", StreamUtils.getStream(stringList).filter(string -> StringHelper.isEmpty((String) string)).count());
        log.info("字符串长度为3的数量为:{}", stringList.stream().filter(string -> string.length() == 3));
        log.info("删除空字符串---筛选后的数据:{}", stringList.stream().filter(string -> StringHelper.isNotEmpty(string)).collect(toList()));
        log.info("合并字符串:{}", stringList.stream().filter(string -> StringHelper.isNotEmpty(string)).collect(Collectors.joining(",hello ")));
    }

    /**
     * @return
     * @author: renshihan@winchannel.net
     * @Description: 去掉重复的结果：
     * @Date 2018/4/28 14:00
     * @Param
     */
    @Test
    public void test2() {
        log.info("去掉重复的结果：{}", StreamUtils.getStream(stringList).distinct().collect(toList()));
    }

    /**
     * @return
     * @author: renshihan@winchannel.net
     * @Description: 截取流的前N个元素：
     * @Date 2018/4/28 14:06
     * @Param
     */
    @Test
    public void test3() {
        log.info("截取流的前N个元素：{}", StreamUtils.getStream(stringList).limit(3).collect(toList()));
    }

    /**
     * @return
     * @author: renshihan@winchannel.net
     * @Description: 跳过流的前n个元素：
     * @Date 2018/4/28 14:06
     * @Param
     */
    @Test
    public void test4() {
        log.info("跳过流的前n个元素：{}", StreamUtils.getStream(stringList).skip(3).collect(toList()));
    }

    /**
     * @return
     * @author: renshihan@winchannel.net
     * @Description: 映射
     * @Date 2018/4/28 14:06
     * @Param
     */
    @Test
    public void test5() {
        log.info("映射 ：{}", StreamUtils.getStream(stringList).map(string -> "映射---" + string).collect(toList()));
    }


    @Test
    public void test6() {
        List<String> list = new ArrayList<String>();
        list.add("I am a boy");
        list.add("I love the girl");
        list.add("But the girl loves another girl");


        log.info("合并流:{}", list.stream()
                .map(line -> line.split(" "))  //按照空格分词
                .flatMap(Arrays::stream)                    //将每个String[]变成Stream后将一个个小流合并成一个大流
//                .distinct()     //去重
                .collect(toList())
        );
    }



}
