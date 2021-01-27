package callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/29 23:50
 */
public class Test {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> futureList = new ArrayList<>();
        Random random = new Random();
        for (int i=0;i<10;i++){
            Integer number = random.nextInt(10);
            FactorialCalculator calculator = new FactorialCalculator(number);
            Future<Integer> future = executor.submit(calculator);
            futureList.add(future);
        }
        do {
            System.out.println("Main: Number of Completed Tasks :"+executor.getCompletedTaskCount());
            for (int i = 0;i<futureList.size();i++){
                Future<Integer> future = futureList.get(i);
                System.out.println("Main: Task "+i+" : "+future.isDone());
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (executor.getCompletedTaskCount()<futureList.size());

        System.out.println("Main : Results:");
        for (int i =0;i<futureList.size();i++){
            Future<Integer> future = futureList.get(i);
            Integer number = null;
            try {
                number = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println("Main: Task "+i+" : "+number);
        }
        executor.shutdown();

    }
}
