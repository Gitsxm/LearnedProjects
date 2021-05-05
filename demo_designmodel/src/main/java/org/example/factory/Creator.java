package org.example.factory;

/**
 * TODO 抽象工厂类
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-25 2:59 PM
 */
public abstract class Creator {
    /**
     * 创建一个产品对象，其输入参数类型可以自行设置
     * 通常为String、Enum、Class等，当然也可以为空
     *
     * @param c
     * @param <T>
     * @return
     */
    public abstract <T extends Product> T createProduct(Class<T> c);
}
