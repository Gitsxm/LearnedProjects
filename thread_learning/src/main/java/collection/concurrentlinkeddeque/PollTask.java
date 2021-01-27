package collection.concurrentlinkeddeque;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.IntStream;

/**
 * TODO TODO 非阻塞线程安全列表 ConcurrentLinkedDeque 删除元素
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/19 9:39
 */
public class PollTask implements Runnable{
    private ConcurrentLinkedDeque<String> list;

    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        IntStream.range(0,5000).forEach(e->{
            list.pollFirst();
            list.pollLast();
        });
    }
}
