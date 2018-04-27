package com.renshihan.base.stream;

import com.renshihan.commons.util.StringHelper;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author renshihan@winchannel.net
 * @date 2018/4/26 16:21
 */
public class Java8Test {
    public static void main(String[] args) {
        //
        Random random=new Random();
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl", "");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        System.out.println("java-8...");
        System.out.println("空字符串数量:" + stringList.stream().filter(string -> StringHelper.isEmpty(string)).count());
        System.out.println("字符串长度为3的数量为：" + stringList.stream().filter(string -> string.length() == 3));
        System.out.println("删除空字符串---筛选后的数据：" + stringList.stream().filter(string -> StringHelper.isNotEmpty(string)).collect(Collectors.toList()));
        System.out.println("合并字符串：" + stringList.stream().filter(string -> StringHelper.isNotEmpty(string)).collect(Collectors.joining(",hello ")));
        System.out.println("square list：" + numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList()));

        System.out.println("------------------------------------------------------------------");
        IntSummaryStatistics starts=numbers.stream().mapToInt(x ->x).summaryStatistics();
        System.out.println("列表中最大的数:"+starts.getMax());
        System.out.println("列表中最小的数:"+starts.getMin());
        System.out.println("所有数之和："+starts.getSum());
        System.out.println("平均数："+starts.getAverage());
        random.ints().limit(10).sorted().forEach(System.out::println);
        System.out.println("随机数:"+"");
        System.out.println("并行处理:"+stringList.parallelStream().filter(string->string.isEmpty()).count());
    }

    private static int getCountEmptyStringUsingJava7(List<String> strings) {
        int count = 0;

        for (String string : strings) {

            if (string.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private static int getCountLength3UsingJava7(List<String> strings) {
        int count = 0;

        for (String string : strings) {
            if (null == string) {
                continue;
            }
            if (string.length() == 3) {
                count++;
            }
        }
        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings) {
        List<String> filteredList = new ArrayList<String>();

        for (String string : strings) {

            if (!string.isEmpty()) {
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String string : strings) {

            if (!string.isEmpty()) {
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length() - 2);
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        List<Integer> squaresList = new ArrayList<Integer>();

        for (Integer number : numbers) {
            Integer square = new Integer(number.intValue() * number.intValue());

            if (!squaresList.contains(square)) {
                squaresList.add(square);
            }
        }
        return squaresList;
    }

    private static int getMax(List<Integer> numbers) {
        int max = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {

            Integer number = numbers.get(i);

            if (number.intValue() > max) {
                max = number.intValue();
            }
        }
        return max;
    }

    private static int getMin(List<Integer> numbers) {
        int min = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            Integer number = numbers.get(i);

            if (number.intValue() < min) {
                min = number.intValue();
            }
        }
        return min;
    }

    private static int getSum(List numbers) {
        int sum = (int) (numbers.get(0));

        for (int i = 1; i < numbers.size(); i++) {
            sum += (int) numbers.get(i);
        }
        return sum;
    }

    private static int getAverage(List<Integer> numbers) {
        return getSum(numbers) / numbers.size();
    }
}
