package com.renshihan.base.引入流;

import com.renshihan.commons.util.StringHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        String abc = "";
        System.out.println(Optional.ofNullable(abc).isPresent());
        System.out.println(Optional.ofNullable(null).isPresent());
        Date a = new Date();
        Date b = new Date(200010L);
        List<Date> ab = Arrays.asList(a, b);
        System.out.println(ab.stream().sorted().collect(toList()));

    }

    @Test
    public void test16() {
        IntStream evenIntSream=IntStream.range(1,100).  //表示取值范围定在1~100之间
                filter(n->n%2==0); //<!>
        log.info("从流中督导：{}",evenIntSream.count() );
    }


    /**
     * 5.6.2数值范围
     */
    @Test
    public void test17(){
        //表示范围是1~100
        IntStream eventNames=IntStream.rangeClosed(1,100)
                //一个从1到100得偶数流
                .filter(n->n%2==0);
        //50
        System.out.println("rangeClosed::"+eventNames.count());

        //表示范围是1~100
        eventNames=IntStream.range(1,100)
                //一个从1到100得偶数流
                .filter(n->n%2==0);

        //49，range是不包含结束值得
        System.out.println("range::"+eventNames.count());
    }


    /**
     * 5.6.3    数值流变化：勾股数
     */
    @Test
    public void test18(){

        Stream<int[]> pythagoreanTriples=
                //创建1~100的范围生成a
                IntStream.rangeClosed(1,100)
                .boxed()
                //扁平化--将多个Stream<int[]>合成一个Stream<int[]>
                .flatMap(
                        a->
                                //创建a~100的b
                                IntStream.rangeClosed(a,100)
                                        //将能a*a+b*b的值开元出整数的b拦截
                                        .filter(b->Math.sqrt(a*a+b*b)%1==0)
                                        //转换b为一个数组[a,b,c]
                                        .mapToObj(b->new int[]{a,b,(int)Math.sqrt(a*a+b*b)})
                );
        pythagoreanTriples.forEach(t->log.info("{},{},{}",t[0],t[1],t[2]));




        //优化
        Stream<double[]> pythagoreanTriples2=IntStream.rangeClosed(1,100).boxed()
                .flatMap(a->IntStream.rangeClosed(a,100).mapToObj(
                        //产生三元数
                        b->new double[]{a,b,Math.sqrt(a*a+b*b)})
                        //元组中的第三个元素必须是整数
                ).filter(t->t[2]%1==0);

    }
    /**
     * 5.7  构建流
     * */
    @Test
    public void test19(){
        //显示的创建一个流
        Stream<String> stringStream=Stream.of("java","8","in","action");
        //字符转换大写并输出
        stringStream.map(String::toUpperCase).forEach(System.out::println);


        //获得一个空的流
        Stream<String> emptyStream=Stream.empty();

        //5.7.2数组创建流
        int[] numbers={1,2,3,4,5,6,7,7,8};
        //计算和
        int sum=Arrays.stream(numbers).sum();
        System.out.println("sum :"+sum);

        //5.7.3文件创建流
        long uniqueWords=0;

        //流会自动关闭
        try(Stream<String> lines= Files.lines(Paths.get("data.txt") ,Charset.defaultCharset())) {
            //
            uniqueWords=lines.flatMap(line->Arrays.stream(line.split(" ")))
                    //删除重复项目
                    .distinct()
                    //数一数多少个不同的单词
                    .count();
        }catch (Exception e){
            //如果打开文件时出现异常
        }

    }
    /**
     * 5.7.4 由函数生成流：创建无限流
     * */
    @Test
    public void test20(){
        //一般会用limit(n)来对这种流加以限制
        //迭代
        Stream.iterate(0,n->n+2).limit(10).forEach(System.out::println);


        //斐波那契元组序列
        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]}).limit(20)
                .forEach(t-> System.out.println("("+t[0]+","+t[1]+")"));

        System.out.println("-----------------------");

        Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]}).limit(20)
                .map(t->t[0])
                .forEach(System.out::println);


    }

    @Test
    public void test21(){
        //generate不是依次对每个新生成的值应用函数的
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
        IntSupplier fib=new IntSupplier(){
            private int previous=0;
            private int current=1;
            @Override
            public int getAsInt() {
                int oldPrevious=this.previous;
                int nextPrevious=this.previous+this.current;
                this.previous=this.current;
                this.current=nextPrevious;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
    @Test
    public void test22(){
        String format="Insert into CASH.T_RCM_DICT (ID,VALUE,LABEL,TYPE,DES,DEL_FLAG,ORDER_NUM,PARENT_ID) values ('%s','%s','%s','%s','%s','1',1,'null');";
        String type="RISK_TIPS";
        String des="商户风险提示";
        System.out.println(String.format(format,IdGenerator.getUUID(),"Y001","机构未正常经营",type,des));
        System.out.println(String.format(format,IdGenerator.getUUID(),"Y002","未在工商注册系统中",type,des));
        System.out.println(String.format(format,IdGenerator.getUUID(),"Y003","负债比过高",type,des));
        System.out.println(String.format(format,IdGenerator.getUUID(),"Y004","有不良记录",type,des));
        System.out.println(String.format(format,IdGenerator.getUUID(),"Y005","大额50000及以上",type,des));
        System.out.println(String.format(format,IdGenerator.getUUID(),"Y006","风险等级低",type,des));
        System.out.println(String.format(format,IdGenerator.getUUID(),"Y007","GPS与商户城市不同",type,des));
        System.out.println(String.format(format,IdGenerator.getUUID(),"Y008","在获取通讯录权限的情况下，通讯录或通话记录未包含紧急联系人2",type,des));
        System.out.println(String.format(format,IdGenerator.getUUID(),"Y009","H5进件",type,des));
        System.out.println(String.format(format,IdGenerator.getUUID(),"Y999","初审和反欺诈没有命中任何一条黄灯规则",type,des));


        type="BASIC_CONS_FLG";
        des="乐融商户风险提示";
        System.out.println(String.format(format,IdGenerator.getUUID(),"C01","结算人与法人始终不一致",type,des));
        System.out.println(String.format(format,IdGenerator.getUUID(),"C02","结算人与法人目前不一致，30天前有过修改",type,des));
        System.out.println(String.format(format,IdGenerator.getUUID(),"C03","结算人与法人始终一致",type,des));
        System.out.println(String.format(format,IdGenerator.getUUID(),"C04","结算人与法人目前一致，30天前有过修改",type,des));

    }

    @Test
    public void test23(){
        //切片
        List<String> demoList=Arrays.asList("str1","str2","str3","st4","123132","124354534","1231215666","wretwe");
        int length=demoList.size();
    }

    @Test
    public void test24(){

    }
}
