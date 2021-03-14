package singleInstance.staticblock;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/2/9 11:13
 */
public class Mythread extends Thread{
    @Override
    public void run() {
        System.out.println(MyObjcet.getInstance().hashCode());
    }
}
