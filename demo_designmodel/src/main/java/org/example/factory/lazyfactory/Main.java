package org.example.factory.lazyfactory;

import org.example.factory.Product;

/**
 * TODO 延迟工厂测试
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-25 4:40 PM
 */
public class Main {
    public static void main(String[] args) {
        Product product = LazyFactory.createProduct("Product1");
        product.method2();
    }
}
