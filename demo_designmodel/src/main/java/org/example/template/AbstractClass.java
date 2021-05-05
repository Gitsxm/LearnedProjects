package org.example.template;

/**
 * TODO 模板方法模式抽象类
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-26 10:47 PM
 */
public abstract class AbstractClass {
    /**
     * 基本方法
     */
    abstract void doSomething();

    /**
     * 基本方法
     */
    abstract void doOtherthing();

    /**
     * 模板方法
     */
    public void templateMethod() {
        /**
         * 调用基本方法完成逻辑
         */
        this.doSomething();
        this.doOtherthing();
    }
}
