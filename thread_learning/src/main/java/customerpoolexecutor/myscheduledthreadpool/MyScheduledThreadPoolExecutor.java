package customerpoolexecutor.myscheduledthreadpool;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO 用来执行 myScheduledTask
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 9:56
 */
public class MyScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {

    public MyScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    @Override
    protected <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable, RunnableScheduledFuture<V> task) {
        MyScheduledTask<V> myScheduledTask = new MyScheduledTask<V>(task,this,runnable,null);
        return myScheduledTask;
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        ScheduledFuture<?> task = super.scheduleAtFixedRate(command,initialDelay,period,unit);
        MyScheduledTask<?> mytask = (MyScheduledTask<?>) task;
        mytask.setPeriod(TimeUnit.MILLISECONDS.convert(period,unit));
        return mytask;
    }
}
