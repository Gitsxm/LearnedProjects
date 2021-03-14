package singleInstance.serializable;

import java.io.Serializable;

/**
 * TODO 序列化与反序列化的单例-
 *
 * @author MGG
 * @version 1.0
 * @date 2021/2/9 10:18
 */
public class MyObject implements Serializable {
    private static final long serialVersionUID = 7506112588728950547L;

    private static class MyObjectHandler {
        private static final MyObject mobject = new MyObject();
    }

    /**
     * 序列化反序列化后不能维持单例
     *
     * @return
     */
    public static MyObject getInstance() {
        return MyObjectHandler.mobject;
    }

    /**
     * 用上反射了，如果有这个方法就会调用，维持单例
     *
     * @return
     */
    protected Object readResolve() {
        System.out.println("调用了 readresolve 方法");
        return MyObjectHandler.mobject;
    }

}
