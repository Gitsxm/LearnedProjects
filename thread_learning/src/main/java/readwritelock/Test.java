package readwritelock;

import java.util.Arrays;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/18 0:37
 */
public class Test {
    public static void main(String[] args) {
        PriceInfo info = new PriceInfo();
        Reader[] readers = new Reader[5];
        Thread[] threads = new Thread[5];
        for (int i=0;i<5;i++){
            readers[i] = new Reader(info);
            threads[i] = new Thread(readers[i]);
        }
        Writer writer = new Writer(info);
        Thread writerThread = new Thread(writer);
        Arrays.stream(threads).forEach(thread -> thread.start());
        writerThread.start();
    }
}
