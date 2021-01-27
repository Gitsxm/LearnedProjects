package customerpoolexecutor.myexecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * TODO 实现了一个定制的执行器，重写了四个方法，使用 concurrenthashmap 来记录运行时间
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/24 22:08
 */
public class Test {
    public static void main(String[] args) {
        MyPoolExecutor executor = new MyPoolExecutor(2,4,1000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>());
        List<Future<String>> futures = new ArrayList<>();
        IntStream.range(0,10).forEach(e->{
            SleepTwoSecondTask task = new SleepTwoSecondTask();
            Future<String> future = executor.submit(task);
            futures.add(future);
        });

        IntStream.range(0,5).forEach(e->{
            try {
                String result = futures.get(e).get();
                System.out.println("Main: Result for task "+e+" :"+result);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }
        });

        executor.shutdown();

        IntStream.range(5,10).forEach(e->{
            try {
                String result = futures.get(e).get();
                System.out.println("Main: Result for task "+e+" :"+result);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }
        });

        try {
            executor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main: executor finished......");
    }
}
