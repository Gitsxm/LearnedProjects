package customerpoolexecutor.myscheduledthreadpool;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 10:08
 */
public class Test {
    public static void main(String[] args) {
        MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(2);
        Task task = new Task();
        System.out.println("Main: "+new Date());
        executor.schedule(task,1, TimeUnit.SECONDS);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task = new Task();
        System.out.println("Main: "+new Date());
        executor.scheduleAtFixedRate(task,1,3,TimeUnit.SECONDS);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main: finished.......");
    }
}
