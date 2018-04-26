package com.renshihan.base.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author renshihan@winchannel.net
 * @date 2018/4/26 16:21
 */
public class Java8Test {
    public static void main(String[] args) {
        //
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        System.out.println("java-8...");
        System.out.println("列表："+stringList);

        Integer count=null;
//        count=stringList.stream().filter(string ->string.isEmpty()).collect(Collectors.joining("'));
    }

    private static int getCountEmptyStringUsingJava7(List<String> strings){
        int count = 0;

        for(String string: strings){

            if(string.isEmpty()){
                count++;
            }
        }
        return count;
    }

    private static int getCountLength3UsingJava7(List<String> strings){
        int count = 0;

        for(String string: strings){

            if(string.length() == 3){
                count++;
            }
        }
        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings){
        List<String> filteredList = new ArrayList<String>();

        for(String string: strings){

            if(!string.isEmpty()){
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator){
        StringBuilder stringBuilder = new StringBuilder();

        for(String string: strings){

            if(!string.isEmpty()){
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length()-2);
    }

    private static List<Integer> getSquares(List<Integer> numbers){
        List<Integer> squaresList = new ArrayList<Integer>();

        for(Integer number: numbers){
            Integer square = new Integer(number.intValue() * number.intValue());

            if(!squaresList.contains(square)){
                squaresList.add(square);
            }
        }
        return squaresList;
    }

    private static int getMax(List<Integer> numbers){
        int max = numbers.get(0);

        for(int i=1;i < numbers.size();i++){

            Integer number = numbers.get(i);

            if(number.intValue() > max){
                max = number.intValue();
            }
        }
        return max;
    }

    private static int getMin(List<Integer> numbers){
        int min = numbers.get(0);

        for(int i=1;i < numbers.size();i++){
            Integer number = numbers.get(i);

            if(number.intValue() < min){
                min = number.intValue();
            }
        }
        return min;
    }

    private static int getSum(List numbers){
        int sum = (int)(numbers.get(0));

        for(int i=1;i < numbers.size();i++){
            sum += (int)numbers.get(i);
        }
        return sum;
    }

    private static int getAverage(List<Integer> numbers){
        return getSum(numbers) / numbers.size();
    }
}
