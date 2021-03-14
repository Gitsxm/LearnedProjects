package rejectexcutionhandler;

/**
 * TODO 拒绝策略
 *
 * @author MGG
 * @version 1.0
 * @date 2021-03-13 10:46 AM
 */
public class DemoServer implements Runnable {
    @Override
    public void run() {
        System.out.println("DemoServer: "+Thread.currentThread().getId());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
