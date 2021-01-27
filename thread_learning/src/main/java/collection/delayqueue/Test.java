package collection.delayqueue;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/19 11:32
 */
public class Test {
    public static void main(String[] args) {
        DelayQueue<Event> queue = new DelayQueue<>();
        Thread[] threads = new Thread[5];
        IntStream.range(0, 5).forEach(e -> {
            Task task = new Task(e + 1, queue);
            threads[e] = new Thread(task);
        });
        IntStream.range(0, 5).forEach(e -> {
            threads[e].start();
        });
        IntStream.range(0, 5).forEach(e -> {
            try {
                threads[e].join();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
        do {
            int counter = 0;
            Event event;
            do {
                event = queue.poll();
                if (event != null) {
                    counter++;
                }
            } while (event != null);
            System.out.println("Main: At " + new Date() + " you have read " + counter + " events");
        } while (queue.size() > 0);
    }
}
