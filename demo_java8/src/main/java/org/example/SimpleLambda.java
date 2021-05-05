package org.example;

import org.example.entity.GreetingService;
import org.example.entity.MathOperation;

/**
 * lambda 表达式
 * GreetingService 无返回值方法，MathOperation 有返回值方法。
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/30 22:17
 */
public class SimpleLambda {
    static String hello = "hello ";
    String hello2 = "hello ";

    private static int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) {
        // 直接在方法中写实现方式
        System.out.println("5+9=" + operate(5, 9, (int i, int j) -> i + j));
        System.out.println("5*9=" + operate(5, 9, (i, j) -> i * j));
        System.out.println("45/9=" + operate(45, 9, (i, j) -> {
            return i / j;
        }));

        // 将实现方式定义为局部变量
        //参数不带括号，方法体带 {}，单条表达式不用{}
        GreetingService service1 = msg -> {
            System.out.println("hello " + msg);
        };
        //参数带括号，方法体不带 {}，四种情况排列组合，但是多个表达式的方法是肯定要带 {} 的
        GreetingService service2 = (msg) -> System.out.println("hello " + msg);
        service1.sayMessage("world!");
        service2.sayMessage("mgg!");
        //直接引用局部变量
        String str1 = "hello ";
        GreetingService service3 = msg -> System.out.println(str1 + msg);
        service3.sayMessage("mgg!");

        //Variable 'str1' is already defined in the scope  不允许定义与外部局部变量同名的局部参数
        //GreetingService service4 = str1 -> System.out.println(str1);

        //外部成员变量必须使用static修饰，毕竟lambda表达式相当于是接口实现类的域
        GreetingService service5 = msg -> System.out.println(hello + msg);
        service5.sayMessage("mgg!");

        //Non-static field 'hello2' cannot be referenced from a static context
//        GreetingService service6 = msg -> System.out.println(hello2 + msg);
    }
}
