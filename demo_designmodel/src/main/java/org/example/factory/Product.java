package org.example.factory;

/**
 * TODO 产品抽象类
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-25 2:56 PM
 */
public abstract class Product {

    /**
     * 产品公共方法
     */
    public void method1(){
        System.out.println("method1.....");
    }

    /**
     * 抽象方法
     */
    public abstract void method2();
}
