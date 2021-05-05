package org.example.factory.simplefactory;

import org.example.factory.Product;

/**
 * TODO 简单工厂类，无抽象父类
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-25 3:21 PM
 */
public class SimpleFactory {

    /**
     * 使用静态方法创建对象
     *
     * @param c
     * @param <T>
     * @return
     */
    public static <T extends Product> T createProduct(Class<T> c) {
        Product product = null;
        try {
            product = (Product) Class.forName(c.getName()).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}
