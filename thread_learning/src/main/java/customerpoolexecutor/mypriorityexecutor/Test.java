package customerpoolexecutor.mypriorityexecutor;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * TODO 优先级执行器只需要将 PriorityBlockingQueue 作为参数传入
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 8:56
 */
public class Test {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,2,1, TimeUnit.SECONDS,new PriorityBlockingQueue<>());
        //先放四个
        IntStream.range(0,4).forEach(e->{
            MyPriorityTask task = new MyPriorityTask(e,"Task "+e);
            executor.execute(task);
        });
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //再放四个
        IntStream.range(4,8).forEach(e->{
            MyPriorityTask task = new MyPriorityTask(e,"Task "+e);
            executor.execute(task);
        });
        executor.shutdown();
        try {
            executor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main: finished....");
    }
}
