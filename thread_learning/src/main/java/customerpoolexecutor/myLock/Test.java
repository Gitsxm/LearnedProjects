package customerpoolexecutor.myLock;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/26 14:39
 */
public class Test {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        IntStream.range(0, 10).forEach(e -> {
            Task task = new Task(lock, "Task" + e);
            Thread thread = new Thread(task);
            thread.start();
        });

        boolean value = true;
        do {
            try {
                value = lock.tryLock(1, TimeUnit.SECONDS);
                if (!value) {
                    System.out.println("Main: Trying to get the Lock..");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                value = false;
            }
        } while (!value);
        System.out.println("Main: get lock..");
        lock.unlock();
        System.out.println("Main: finished ...");
    }
}
