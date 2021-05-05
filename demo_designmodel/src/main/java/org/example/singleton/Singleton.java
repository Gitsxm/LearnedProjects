package org.example.singleton;

/**
 * TODO 单例模式通用范例，饿汉模式
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-06 11:11 PM
 */
public class Singleton {

    /**
     * 在类加载时就完成了对象的创建
     */
    private final static Singleton INSTANCE = new Singleton();

    /**
     * 防止产生多个对象，构造函数私有化
     */
    private Singleton() {
    }

    /**
     * 获取单例对象
     *
     * @return
     */
    public static Singleton getInstance() {
        return INSTANCE;
    }

}