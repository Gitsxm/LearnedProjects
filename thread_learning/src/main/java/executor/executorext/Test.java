package executor.executorext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021-03-13 4:10 PM
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService pool = new ThreadPoolExecutor(5,5,0L, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("Thread execut before: thread name: "+((MyTask)r).getName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("Thread execut after: thread name: "+((MyTask)r).getName());
            }

            @Override
            protected void terminated() {
                System.out.println("terminated....");
            }
        };

        for (int i=0;i<10;i++){
            MyTask task = new MyTask("task"+i);
            pool.execute(task);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
    }
}
