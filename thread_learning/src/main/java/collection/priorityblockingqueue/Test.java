package collection.priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.IntStream;

/**
 * TODO 创建五个线程放入 队列中放入5000元素 每个元素都有对应的优先级，poll 方法按优先级返回。
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/19 11:05
 */
public class Test {
    public static void main(String[] args) {
        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();
        Thread[] threads = new Thread[5];

        IntStream.range(0,threads.length).forEach(e->{
            Task task = new Task(e,queue);
            threads[e] = new Thread(task);
        });

        IntStream.range(0,threads.length).forEach(e->{
            threads[e].start();
        });

        IntStream.range(0,threads.length).forEach(e->{
            try {
                threads[e].join();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });

        System.out.println("Main: Queue Size: "+queue.size());
        IntStream.range(0,threads.length*1000).forEach(e->{
            Event event = queue.poll();
            System.out.println("Thread "+event.getThread()+" Priority "+event.getPriority());
        });
        System.out.println("Main: Queue Size :"+queue.size());
        System.out.println("Main: End of the program...");

    }
}
