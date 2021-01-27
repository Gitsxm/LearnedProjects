package customerpoolexecutor.myexecutor;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * TODO 一个线程任务
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/24 22:04
 */
public class SleepTwoSecondTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return new Date().toString();
    }
}
