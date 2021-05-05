package org.example.singleton;

/**
 * TODO 性能更好的双检锁单例模式-懒汉模式
 *
 * @author MGG
 * @version 1.0
 * @date 2021/2/9 9:30
 */
public class SingletonLazy {
    private volatile static SingletonLazy myObject;

    /**
     * 构造函数私有化
     */
    private SingletonLazy() {
    }

    /**
     * DCL双检测机制提升性能
     *
     * @return
     */
    public static SingletonLazy getInstance() {
        if (null != myObject) {
        } else {
            //创建对象前的准备工作
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (SingletonLazy.class) {
                if (null == myObject) {
                    myObject = new SingletonLazy();
                }
            }
        }
        return myObject;
    }
}