package org.example.abstractfactory;

/**
 * TODO 具体实现类 ProductA
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-26 9:47 PM
 */
public class ProductA extends AbstractProductA {
    @Override
    public void doSomething() {
        System.out.println("A1 doSomething...");
    }
}
