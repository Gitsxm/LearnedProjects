package org.example.factory.singletonfactory;

/**
 * TODO 单例工厂测试
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-25 4:12 PM
 */
public class Main {
    public static void main(String[] args) {
        Singleton singleton = SingletonFactory.getSingleton();
        singleton.doSomething();
    }
}
