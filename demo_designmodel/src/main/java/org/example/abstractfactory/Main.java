package org.example.abstractfactory;

/**
 * TODO 只需要调用抽象类即可获得实际对象
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-26 9:57 PM
 */
public class Main {
    public static void main(String[] args) {
        AbstractCreator creator1 = new Creator1();
        AbstractCreator creator2 = new Creator2();
        AbstractProductA a1 = creator1.createProductA();
        AbstractProductA a2 = creator2.createProductA();
        AbstractProductB b1 = creator1.createProductB();
        AbstractProductB b2 = creator2.createProductB();
        a1.doSomething();
        a2.doSomething();
        b1.doSomething();
        b2.doSomething();
    }
}
