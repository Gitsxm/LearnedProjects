package customerpoolexecutor.myscheduledthreadpool;

import com.sun.corba.se.spi.orbutil.closure.Closure;

import java.util.Date;
import java.util.concurrent.*;

/**
 * TODO 定制运行在定时线程池中的任务
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 9:40
 */
public class MyScheduledTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {
    private RunnableScheduledFuture<V> task;
    private ScheduledThreadPoolExecutor executor;
    private long period;
    private long startDate;

    public MyScheduledTask(RunnableScheduledFuture<V> task, ScheduledThreadPoolExecutor executor,Runnable runnable,V result) {
        super(runnable,result);
        this.task = task;
        this.executor = executor;
    }

    @Override
    public boolean isPeriodic() {
        return task.isPeriodic();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        if (!isPeriodic() || startDate == 0){
            return task.getDelay(unit);
        }
        Date now = new Date();
        long delay = startDate-now.getTime();
        return unit.convert(delay,TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return task.compareTo(o);
    }

    @Override
    public void run() {
        if (isPeriodic() && !executor.isShutdown()){
            Date now = new Date();
            startDate = now.getTime()+period;
            executor.getQueue().add(this);
        }
        System.out.println("Pre-MyScheduledTask : "+ new Date());
        System.out.println("MyScheduledTask : Is Periodic "+isPeriodic());
        super.runAndReset();
        System.out.println("Post-MyScheduledTask : "+new Date());
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    public void setPeriod(long period) {
        this.period = period;
    }
}
