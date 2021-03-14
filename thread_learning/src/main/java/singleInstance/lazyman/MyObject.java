package singleInstance.lazyman;

/**
 * TODO 性能更好的单例模式-饿汉模式
 *
 * @author MGG
 * @version 1.0
 * @date 2021/2/9 9:30
 */
public class MyObject {
    private volatile static MyObject myObject;

    /**
     * DCL双检测机制提升性能
     *
     * @return
     */
    public static MyObject getInstance() {
        if (null != myObject) {
        } else {
            //创建对象前的准备工作
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (MyObject.class) {
                if (null == myObject) {
                    myObject = new MyObject();
                }
            }
        }
        return myObject;
    }
}
