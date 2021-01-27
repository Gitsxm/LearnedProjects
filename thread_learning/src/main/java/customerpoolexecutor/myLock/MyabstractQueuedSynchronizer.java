package customerpoolexecutor.myLock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * TODO 实现定制 Lock 类 创建一个临界区判断同步类
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/26 14:22
 */
public class MyabstractQueuedSynchronizer extends AbstractQueuedSynchronizer {
    private AtomicInteger state;

    public MyabstractQueuedSynchronizer() {
        state = new AtomicInteger(0);
    }

    @Override
    protected boolean tryAcquire(int arg) {
        return state.compareAndSet(0,1);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return state.compareAndSet(1,0);
    }
}
