package readwritelock;

import java.util.stream.IntStream;

/**
 * 读取
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/18 0:28
 */
public class Reader implements Runnable {
    private PriceInfo info;

    public Reader(PriceInfo info) {
        this.info = info;
    }

    @Override
    public void run() {
        IntStream.range(0, 10).forEach(e -> {
            System.out.println(Thread.currentThread().getName() + ": Price1 : " + info.getPrice1());
            System.out.println(Thread.currentThread().getName() + ": Price2 : " + info.getPrice2());
        });
    }
}
