package forkjoin.canceltask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/17 22:55
 */
public class Test {
    public static void main(String[] args) {
        ArrayGenerator generator = new ArrayGenerator();
        int arr[] = generator.generator(1000);
        TaskManager manager = new TaskManager();
        ForkJoinPool pool = new ForkJoinPool();
        SearchNumberTask task = new SearchNumberTask(arr,0,1000,5,manager);
        pool.execute(task);
        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main: finished!....");
    }
}
