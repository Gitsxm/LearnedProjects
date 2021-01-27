package collection.priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.IntStream;

/**
 * TODO 设定优先级放入队列
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/19 10:57
 */
public class Task implements Runnable{
    private int id;
    private PriorityBlockingQueue<Event> queue;

    public Task(int id, PriorityBlockingQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        IntStream.range(0,1000).forEach(e->{
            Event event = new Event(id,e);
            queue.add(event);
        });
    }
}
