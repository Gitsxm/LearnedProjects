package rejectexcutionhandler;

import java.util.concurrent.*;

/**
 * TODO 拒绝策略
 *
 * @author MGG
 * @version 1.0
 * @date 2021-03-13 10:48 AM
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService pool = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(10), Executors.defaultThreadFactory()
                , new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString()+" is discord..");
            }
        });
        DemoServer task = new DemoServer();
        for (int i=0;i<Integer.MAX_VALUE;i++){
            pool.submit(task);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
