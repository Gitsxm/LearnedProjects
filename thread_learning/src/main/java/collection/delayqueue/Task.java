package collection.delayqueue;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/19 11:29
 */
public class Task implements Runnable {
    private int id;
    private DelayQueue<Event> queue;

    public Task(int id, DelayQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        Date now = new Date();
        Date delay = new Date();
        delay.setTime(now.getTime() + id * 1000);
        System.out.println("Thread: " + id + " : " + delay);
        IntStream.range(0, 100).forEach(e -> {
            Event event = new Event(delay);
            queue.add(event);
        });
    }
}
