package locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021-03-12 9:52 PM
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        LockSupportDemo t1 = new LockSupportDemo(lock);
        t1.setName("T1");
        LockSupportDemo t2 = new LockSupportDemo(lock);
        t2.setName("T2");
        t1.start();
//        Thread.sleep(2000);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
