package collection.concurrentlinkeddeque;

import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/19 9:41
 */
public class Test {
    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        Thread[] threads = new Thread[100];
        IntStream.range(0, 100).forEach(e -> {
            AddTask task = new AddTask(list);
            threads[e] = new Thread(task);
            threads[e].start();
        });
        System.out.println("Main: " + threads.length + " AddTask threads have been launched");
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Main: Size of the list :" + list.size());

        IntStream.range(0, 100).forEach(e -> {
            PollTask task = new PollTask(list);
            threads[e] = new Thread(task);
            threads[e].start();
        });
        System.out.println("Main: " + threads.length + " PollTask threads have been launched");
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Main: Size of the list :" + list.size());

    }
}
