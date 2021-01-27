package collection.threadlocalrandom;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * TODO 生成并发随机数
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/21 23:45
 */
public class TaskLocalRandom implements Runnable{
    public TaskLocalRandom() {
        ThreadLocalRandom.current();
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        IntStream.range(0,10).forEach(e->{
            System.out.println("Task: "+name+" : "+ThreadLocalRandom.current().nextInt(10));
        });
    }
}
