package customerpoolexecutor.myLock;

import java.util.concurrent.TimeUnit;

/**
 * TODO 实现定制 lock 类 同步任务
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/26 14:33
 */
public class Task implements Runnable{
    private MyLock lock;
    private String name;

    public Task(MyLock lock, String name) {
        this.lock = lock;
        this.name = name;
    }

    @Override
    public void run() {
        lock.lock();
        System.out.println("Task : "+name+" Take the lock");
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Task : "+name+" Free the lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
