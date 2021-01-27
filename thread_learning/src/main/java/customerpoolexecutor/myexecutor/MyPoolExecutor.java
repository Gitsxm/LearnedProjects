package customerpoolexecutor.myexecutor;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * TODO 定制线程执行器
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/24 21:38
 */
public class MyPoolExecutor extends ThreadPoolExecutor {
    private ConcurrentHashMap<String, Date> startTime;
    public MyPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        startTime = new ConcurrentHashMap<>();
    }

    @Override
    public void shutdown() {
        System.out.println("MyPoolExecutor : going to shutdown...");
        System.out.println("MyPoolExecutor : executed tasks..."+getCompletedTaskCount());
        System.out.println("MyPoolExecutor : running tasks..."+getActiveCount());
        System.out.println("MyPoolExecutor : pending tasks..."+getQueue().size());
        super.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        System.out.println("MyPoolExecutor : Going to immediately shutdown...");
        System.out.println("MyPoolExecutor : executed tasks..."+getCompletedTaskCount());
        System.out.println("MyPoolExecutor : running tasks..."+getActiveCount());
        System.out.println("MyPoolExecutor : pending tasks..."+getQueue().size());
        return super.shutdownNow();
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("MyPoolExecutor : a task is begining ..."+t.getName()+" : "+r.hashCode());
        startTime.put(String.valueOf(r.hashCode()),new Date());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Future<?> result = (Future<?>) r;
        System.out.println("****************************************");
        System.out.println("MyPoolExecutor : a task is finishing...");
        try {
            System.out.println("MyPoolExecutor : result :"+result.get());
            Date startDate = startTime.remove(String.valueOf(r.hashCode()));
            Date finishDate = new Date();
            long diff = finishDate.getTime()-startDate.getTime();
            System.out.println("MyPoolExecutor : durating......"+diff);
            System.out.println("*****************************************");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        super.afterExecute(r, t);
    }
}
