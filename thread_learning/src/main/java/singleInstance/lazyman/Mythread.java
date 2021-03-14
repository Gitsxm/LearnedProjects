package singleInstance.lazyman;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/2/9 9:46
 */
public class Mythread extends Thread {

    @Override
    public void run() {
        System.out.println(MyObject.getInstance().hashCode());
    }
}
