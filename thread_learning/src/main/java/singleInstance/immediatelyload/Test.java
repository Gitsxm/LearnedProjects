package singleInstance.immediatelyload;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/2/9 10:10
 */
public class Test {
    public static void main(String[] args) {
        Mythread mythread = new Mythread();
        Mythread mythread1 = new Mythread();
        Mythread mythread2 = new Mythread();
        mythread.start();
        mythread1.start();
        mythread2.start();
    }
}
