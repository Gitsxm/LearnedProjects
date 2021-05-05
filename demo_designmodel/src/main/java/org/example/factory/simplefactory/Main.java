package org.example.factory.simplefactory;

import org.example.factory.ConcreteProduct1;
import org.example.factory.Product;

/**
 * TODO 简单工厂模式测试
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-25 3:28 PM
 */
public class Main {
    public static void main(String[] args) {
        Product product = SimpleFactory.createProduct(ConcreteProduct1.class);
        product.method2();
    }
}
