package multiple_condition_lock;

import java.util.Arrays;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/20 22:04
 */
public class Test {
    public static void main(String[] args) {
        FileMock mock = new FileMock(100, 1);
        Buffer buffer = new Buffer(10);
        Producer producer = new Producer(mock, buffer);
        Thread prodThread = new Thread(producer, "Producer");
        Consumer[] consumers = new Consumer[3];
        Thread[] csmThreads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            consumers[i] = new Consumer(buffer);
            csmThreads[i] = new Thread(consumers[i], "Consumer" + i);
        }
        prodThread.start();
        Arrays.stream(csmThreads).forEach(e -> e.start());
    }
}
