package org.example.factory.multifactory;

import org.example.factory.Product;

/**
 * TODO 多工程模式不需要传递参数来创建对象了。
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-25 3:55 PM
 */
public class Main {
    public static void main(String[] args) {
        Product product = (new Product1Factory()).createProduct();
        product.method2();
    }
}
