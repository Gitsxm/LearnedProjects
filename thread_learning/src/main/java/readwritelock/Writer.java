package readwritelock;

import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/18 0:34
 */
public class Writer implements Runnable {
    private PriceInfo info;

    public Writer(PriceInfo info) {
        this.info = info;
    }

    @Override
    public void run() {
        IntStream.range(0, 3).forEach(e -> {
            System.out.println("Wirter : attempt to modify price....");
            info.setPrice(Math.random() * 10, Math.random() * 10);
            System.out.println("Modify success!");
            try {
                Thread.sleep(1);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
    }
}
