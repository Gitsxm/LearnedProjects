package org.example;

/**
 * 接口默认、静态方法
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/2 21:33
 */
public class SimpleDefaultInterface {
    public static void main(String[] args) {

    }

    /**
     * 基本形式
     */
    interface DefaultInterface{
        //默认方法
        default void print(){
            System.out.println("i am a car !");
        }
        //静态方法
        static void staticPrint(){
            System.out.println("interface static method");
        }
    }

    interface OneWheeler{
        default void print(){
            System.out.println("i am a one wheel car !");
        }
    }

    /**
     * 1. 当实现了多个含同名默认方法的接口时需要对方法进行重写，实现单个接口不需要。
     * 2. 或者调用父接口的默认方法
     */
    class Car implements DefaultInterface,OneWheeler{
        @Override
        public void print() {
            //重写
//            System.out.println("car run !");
            //使用父方法
            DefaultInterface.super.print();
        }
    }
}
