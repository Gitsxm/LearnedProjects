package interrupt;

/**
 * TODO 线程运行五秒后中断
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/9 16:13
 */
public class Test {
    public static void main(String[] args) {
        PrimeNumberGenertator thread = new PrimeNumberGenertator();
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
