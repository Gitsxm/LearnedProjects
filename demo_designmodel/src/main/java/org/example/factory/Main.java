package org.example.factory;

/**
 * TODO 工厂类测试方法
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-25 3:06 PM
 */
public class Main {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product = creator.createProduct(ConcreteProduct1.class);
        product.method2();
    }
}
