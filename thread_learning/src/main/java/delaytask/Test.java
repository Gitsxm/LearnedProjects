package delaytask;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO 创建五个任务 每隔一秒有一个任务执行
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/7 19:51
 */
public class Test {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        System.out.println("Main: Starting at "+ LocalDateTime.now());
        for (int i=0;i<5;i++){
            Task task = new Task("Task "+i);
            executor.schedule(task,i+1, TimeUnit.SECONDS);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main: Ends at ..."+LocalDateTime.now());
    }
}
