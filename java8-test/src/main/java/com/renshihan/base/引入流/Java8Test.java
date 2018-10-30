package com.renshihan.base.引入流;

import com.renshihan.commons.util.StringHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

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
    private static final List<Integer> numbers2 = Arrays.asList(2, 3, 7, 3, 5);

    public static void main(String[] args) {
        System.out.println("---->" + numbers.containsAll(numbers2));
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


    /**
     * 谓词
     */
    @Test
    public void test7() {
        //返回所有列中的偶数，并保证没有重复
        List<Integer> numbers = Arrays.asList(1, 2, 4, 5, 6, 7);
        numbers.stream().filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射
     */
    @Test
    public void test8() {

        List<String> names = Arrays.asList("张三", "里斯", "王五");
        names.stream().
                map(name -> String.format("名字叫:%s", name)).
                collect(toList()).
                forEach(System.out::println);
    }

    /**
     * 流的扁平化
     */
    @Test
    public void test9() {
        List<String> hello = Arrays.asList("Hll", "eel", "lle", "l", "o", " ", "W", "o", "r", "l", "d");

        hello.stream()
                //将每一个单词转换为由其字母构成的数组
                .map(a -> a.split(""))
                //将各个生成流扁平化为单个流
                .flatMap(Arrays::stream)

                .distinct()
                .collect(toList()).forEach(System.out::println);
    }

    /**
     * 测试：给定一个数字列表，如何返回一个由每个数的平方构成的列表
     */
    @Test
    public void test10() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream().map(number -> number * number).collect(toList()).forEach(System.out::println);
    }

    @Test
    public void test11() {
        List<Integer> ns1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> ns2 = Arrays.asList(5, 6, 7);
        ns1.stream()
                .flatMap(
                        n1 -> ns2.stream()
                                .filter(n2 -> (n1 + n2) % 3 == 0)
                                .map(n2 -> String.format("(%s,%s)", n1, n2)))
                .collect(toList())
                .forEach(System.out::println);

    }

    /**
     * 查找和匹配
     */
    public void test12() {
        List<Integer> numbers = Arrays.asList(1, 2, 4, 5, 6, 44, 87, 66, 5, 4, 5, 6, 66, 54, 4, 5, 5, 5);
        numbers.stream().filter(i -> i % 2 == 0).findFirst().isPresent();

    }

    /**
     * 归约   函数式编程语言称为-折叠：
     * 场景：元素求和，
     * reduce
     */
    @Test
    public void test13() {
        //或者
        int sum = numbers.stream().reduce(0, Integer::sum);
        //重载 无初始值
        Optional<Integer> sumOptional = numbers.stream().reduce((a, b) -> a + b);

        System.out.println(sum);

        //最大最小值

        System.out.println("最大值：" + numbers2.parallelStream().reduce(Integer::max).get());
        System.out.println("最小值：" + numbers2.stream().reduce(Integer::min).get());
    }

    @Test
    public void test14() {
        //数一数流中有多少个元素
        Integer sum = numbers2.stream().map(p -> 1).reduce(Integer::sum).get();
        System.out.println(sum);
    }

    @Test
    public void test15() {
        //1.找出2011年发生的所有交易，并按照交易额排名

    }


}
