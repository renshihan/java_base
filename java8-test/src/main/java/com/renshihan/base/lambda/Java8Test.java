package com.renshihan.base.lambda;

/**
 * @author renshihan@winchannel.net
 * @date 2018/4/26 9:27
 * <p>
 * 以下是lambda表达式的重要特征:
 * <p>
 * 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
 * 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
 * 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
 * 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
 */
public class Java8Test {
    private static final String testFinal="只能用外部final定义的局部变量";
    private static final Java8Test java8Test=new Java8Test();
    public static void main(String[] args) {

        //类型声明
        MathOperation addtion = (int a, int b) -> a + b;

        //不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        //大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a + b;
        };

        //没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println(java8Test.operator(1,2,addtion));
        System.out.println(java8Test.operator(1,2,subtraction));
        System.out.println(java8Test.operator(1,2,multiplication));
        System.out.println(java8Test.operator(1,2,division));

        //不用括号
        GreentingService greentingService=message ->
                System.out.println("hello "+message);
        //用括号
        GreentingService greentingService1=(message)->
                System.out.println("hello "+message);

        greentingService.sayMessage("111222");
        greentingService1.sayMessage("222333");

        greentingService.sayMessage(testFinal);



    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreentingService {
        void sayMessage(String message);
    }

    private int operator(int a, int b, MathOperation mathOperation) {

        return mathOperation.operation(a, b);
    }

}
