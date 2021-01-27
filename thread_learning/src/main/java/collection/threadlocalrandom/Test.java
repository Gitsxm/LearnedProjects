package collection.threadlocalrandom;

import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/21 23:49
 */
public class Test {
    public static void main(String[] args) {

        Thread[] threads = new Thread[3];
        IntStream.range(0,3).forEach(value -> {
            TaskLocalRandom random = new TaskLocalRandom();
            threads[value] = new Thread(random);
            threads[value].start();
        });
    }
}
