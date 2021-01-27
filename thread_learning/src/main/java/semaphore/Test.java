package semaphore;

import java.util.Arrays;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/22 10:46
 */
public class Test {
    public static void main(String[] args) {
        PrintQueue queue = new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(queue), "thread" + i);
        }

        Arrays.stream(threads).forEach(e -> e.start());
    }
}
