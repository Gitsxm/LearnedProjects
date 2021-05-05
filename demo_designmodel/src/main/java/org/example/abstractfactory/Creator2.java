package org.example.abstractfactory;

/**
 * TODO 生成产品等级 2 的产品
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-26 9:56 PM
 */
public class Creator2 extends AbstractCreator{
    @Override
    public AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}
