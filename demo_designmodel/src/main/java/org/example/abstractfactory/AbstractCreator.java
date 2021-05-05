package org.example.abstractfactory;

/**
 * TODO 抽象工厂类
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-26 9:49 PM
 */
public abstract class AbstractCreator {
    /**
     * 生产产品A家族
     *
     * @return
     */
    public abstract AbstractProductA createProductA();

    /**
     * 生产产品B家族
     *
     * @return
     */
    public abstract AbstractProductB createProductB();

}
