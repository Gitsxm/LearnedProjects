package customerpoolexecutor.myforkjoincustomthread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * TODO 通过 ThreadFactory 为 fork/join 生成定制线程 --threadfactory
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 22:52
 */
public class MyWorkerThreadFactory implements ForkJoinPool.ForkJoinWorkerThreadFactory {
    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
        return new MyWorkThread(pool);
    }
}
