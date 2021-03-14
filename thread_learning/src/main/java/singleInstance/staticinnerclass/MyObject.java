package singleInstance.staticinnerclass;

/**
 * TODO 单例-使用静态内部类实现
 *
 * @author MGG
 * @version 1.0
 * @date 2021/2/9 10:12
 */
public class MyObject {
    private static class MyObjectHandler {
        private static MyObject myObject = new MyObject();
    }

    public MyObject() {
    }

    public static MyObject getInstance() {
        return MyObjectHandler.myObject;
    }
}
