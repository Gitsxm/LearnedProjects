package cycletask;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * TODO 创建一个周期执行任务，通过手动延迟查看周期运行情况。
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/7 20:09
 */
public class Test {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        System.out.println("Main: Starting at:"+ LocalDateTime.now());
        Task task = new Task("TaskTarget");
        ScheduledFuture<?> result = executorService.scheduleAtFixedRate(task,1,2, TimeUnit.SECONDS);
        for (int i=0;i<10;i++){
            System.out.println("Main:Delay:"+result.getDelay(TimeUnit.MILLISECONDS));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main:Finished at:"+ LocalDateTime.now());
    }
}
