package customerpoolexecutor.executorwiththreadfactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TODO 在 Executor 对象中使用 MyThreadFactory
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 9:31
 */
public class Test {
    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
        ExecutorService executor = Executors.newCachedThreadPool(factory);
        MyTask task = new MyTask();
        executor.submit(task);
        executor.shutdown();;
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main: end...");
    }
}
