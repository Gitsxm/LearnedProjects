package collection.concurrentlinkeddeque;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.IntStream;

/**
 * TODO 非阻塞线程安全列表 ConcurrentLinkedDeque 添加元素
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/19 9:35
 */
public class AddTask implements Runnable{
    private ConcurrentLinkedDeque<String> list;

    public AddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        IntStream.range(0,10000).forEach(e->{
            list.add(name+": Element"+e);
        });
    }
}
