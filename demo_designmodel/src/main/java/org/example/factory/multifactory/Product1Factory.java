package org.example.factory.multifactory;

import org.example.factory.ConcreteProduct1;
import org.example.factory.Product;

/**
 * TODO 只负责 ConcreteProduct1 的创建
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-25 3:45 PM
 */
public class Product1Factory extends AbstractFactory {
    @Override
    public Product createProduct() {
        return new ConcreteProduct1();
    }
}
