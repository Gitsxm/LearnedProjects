package threadfactory;

import java.util.concurrent.TimeUnit;

/**
 * 工厂示例线程
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/16 22:46
 */
public class Task implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
