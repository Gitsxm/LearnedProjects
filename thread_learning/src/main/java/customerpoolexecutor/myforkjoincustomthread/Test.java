package customerpoolexecutor.myforkjoincustomthread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * TODO 大概就是这几步的调用方法，由于书上参考代码不全，也不知道逻辑，就没运行。我们定制了一个线程类，通过定制的线程类定制了fork task；
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 23:23
 */
public class Test {
    public static void main(String[] args) {
        MyWorkerThreadFactory factory = new MyWorkerThreadFactory();
        ForkJoinPool pool = new ForkJoinPool(4,factory,null,false);
        int[] arr = new int[1000];
        for (int i=0;i<1000;i++){
            arr[i] = 1;
        }
        MyRecursiveTask task = new MyRecursiveTask(arr,0,1000);
        pool.execute(task);

        task.join();
        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main : end....");
    }
}
