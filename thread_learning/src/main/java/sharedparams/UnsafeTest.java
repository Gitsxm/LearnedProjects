package sharedparams;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/15 10:36
 */
public class UnsafeTest {
    public static void main(String[] args) {
        UnsafeTask task = new UnsafeTask();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
