package solverefusetask;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO 执行器接收任务执行，判断是否调用了 shutdown 如果是就拒绝执行，找到 exceptionHandler 处理被拒绝的任务。
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/15 10:04
 */
public class Test {
    public static void main(String[] args) {
        RejectedTaskController controller = new RejectedTaskController();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.setRejectedExecutionHandler(controller);
        System.out.println("Main: Starting....");
        for (int i=0;i<3;i++){
            Task task = new Task("Task "+i);
            executor.submit(task);
        }
        System.out.println("Main: Shutting down the Executor...");
        executor.shutdown();
        System.out.println("Main: Sending another Task...");
        Task task = new Task("RejectedTask");
        executor.submit(task);
        System.out.println("Main: End");
    }
}
