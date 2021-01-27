package customerpoolexecutor.myforkjoincustomthread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * TODO 通过 ThreadFactory 为 fork/join 生成定制线程
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 22:46
 */
public class MyWorkThread extends ForkJoinWorkerThread {
    private static ThreadLocal<Integer> taskCounter = new ThreadLocal<>();
    /**
     * Creates a ForkJoinWorkerThread operating in the given pool.
     *
     * @param pool the pool this thread works in
     * @throws NullPointerException if pool is null
     */
    protected MyWorkThread(ForkJoinPool pool) {
        super(pool);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("MyWorkThread : init task counter ....");
        taskCounter.set(0);
    }

    @Override
    protected void onTermination(Throwable exception) {
        System.out.println("MyWorkThread : "+getId()+" : "+taskCounter.get());
        super.onTermination(exception);
    }

    public void addTask(){
        int counter = taskCounter.get().intValue();
        counter++;
        taskCounter.set(counter);
    }
}
