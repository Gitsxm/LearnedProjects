package org.example.factory.lazyfactory;

import org.example.factory.ConcreteProduct1;
import org.example.factory.Product;
import org.example.factory.ConcreteProduct2;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 延迟工厂
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-25 4:34 PM
 */
public class LazyFactory {
    private static final Map<String, Product> prMap = new HashMap();

    public static synchronized Product createProduct(String type) {
        Product product = null;
        //如果Map中已经有这个对象
        if (prMap.containsKey(type)) {
            product = prMap.get(type);
        } else {
            if (type.equals("Product1")) {
                product = new ConcreteProduct1();
            } else {
                product = new ConcreteProduct2();
            }
            //同时把对象放到缓存容器中
            prMap.put(type, product);
        }
        return product;
    }
}

