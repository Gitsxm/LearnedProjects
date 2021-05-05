package org.example.factory.singletonfactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * TODO 单例工厂
 * 需要通过反射来创建对象
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-25 4:07 PM
 */
public class SingletonFactory {
    private static Singleton singleton;

    //类加载时创建 singleton 对象
    static {
        try {
            Class clz = Class.forName(Singleton.class.getName());
            //获取构造方法
            Constructor constructor = clz.getDeclaredConstructor();
            //设置无参构造可访问
            constructor.setAccessible(true);
            //创建对象
            singleton = (Singleton) constructor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static Singleton getSingleton() {
        return singleton;
    }
}
