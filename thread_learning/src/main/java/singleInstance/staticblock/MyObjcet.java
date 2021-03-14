package singleInstance.staticblock;

/**
 * TODO 单例模式-使用静态块
 *
 * @author MGG
 * @version 1.0
 * @date 2021/2/9 11:11
 */
public class MyObjcet {
    private static MyObjcet myObjcet = null;

    static {
        myObjcet = new MyObjcet();
    }

    public static MyObjcet getInstance() {
        return myObjcet;
    }
}
