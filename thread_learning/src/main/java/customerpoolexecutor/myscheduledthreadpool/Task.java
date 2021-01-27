package customerpoolexecutor.myscheduledthreadpool;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 10:07
 */
public class Task implements Runnable{
    @Override
    public void run() {
        System.out.println("Task: Begin.");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task: End.");
    }
}
