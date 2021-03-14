package singleInstance.useenum;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/2/9 11:23
 */
public class Mythread extends Thread{
    @Override
    public void run() {
        System.out.println(MyObject.getConnection().hashCode());
    }
}
