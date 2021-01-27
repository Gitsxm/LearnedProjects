package customerpoolexecutor.mythreadfactory;

import java.util.concurrent.ThreadFactory;

/**
 * TODO 创建定制线程工厂类
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 9:19
 */
public class MyThreadFactory implements ThreadFactory {
    private int counter;
    private String prefix;

    public MyThreadFactory(String prefix) {
        this.prefix = prefix;
        counter = 1;
    }

    @Override
    public Thread newThread(Runnable r) {
        MyThread thread = new MyThread(r,prefix+"-"+counter);
        counter++;
        return thread;
    }
}
