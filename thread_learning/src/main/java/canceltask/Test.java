package canceltask;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO 执行器中取消任务 两秒内任务执行四次
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/7 20:41
 */
public class Test {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Task task = new Task();
        System.out.println("Main: task runing ....");
        Future<String> result = executor.submit(task);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main: Cancel task...");
        result.cancel(true);

        System.out.println("Main:"+result.isCancelled());
        System.out.println("Main:"+result.isDone());
        executor.shutdown();
        System.out.println("Main: Executor is finished!");
    }
}
