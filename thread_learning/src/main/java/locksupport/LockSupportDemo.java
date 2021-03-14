package locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021-03-12 9:49 PM
 */
public class LockSupportDemo extends Thread{
    private final Object lock;

    public LockSupportDemo(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println("Thread: "+Thread.currentThread().getName());
            LockSupport.park();
        }
    }
}
