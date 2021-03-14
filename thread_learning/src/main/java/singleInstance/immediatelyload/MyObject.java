package singleInstance.immediatelyload;

/**
 * TODO 单例立即加载模式
 *
 * @author MGG
 * @version 1.0
 * @date 2021/2/9 9:30
 */
public class MyObject {
    private static MyObject myObject = new MyObject();

    /**
     * 立即加载模式本案例不能有其他实例变量，不然会导致线程安全问题。
     * @return
     */
    public static MyObject getInstance() {
        return myObject;
    }
}
