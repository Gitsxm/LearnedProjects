package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打印队列
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/17 23:12
 */
public class PrintQueue {
//    private final Lock queueLock = new ReentrantLock();
    //带参数的锁，true 为公平模式，等待最久的线程会先拿到资源
    private final Lock queueLock = new ReentrantLock(true);

    public void printJob(Object document){
        queueLock.lock();
        int duration = (int) (Math.random()*10000);
        System.out.println(Thread.currentThread().getName()+"PrintQueue: print job in "+ duration/1000 +"s!");
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }
    }
}
