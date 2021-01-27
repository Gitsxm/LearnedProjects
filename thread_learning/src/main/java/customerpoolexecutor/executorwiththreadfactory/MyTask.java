package customerpoolexecutor.executorwiththreadfactory;

import java.util.concurrent.TimeUnit;

/**
 * TODO 创建任务
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 9:22
 */
public class MyTask implements Runnable{
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
