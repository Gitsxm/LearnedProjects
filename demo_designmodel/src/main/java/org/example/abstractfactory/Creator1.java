package org.example.abstractfactory;

/**
 * TODO 生产产品等级为 1 的产品
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-26 9:54 PM
 */
public class Creator1 extends AbstractCreator {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB();
    }
}
