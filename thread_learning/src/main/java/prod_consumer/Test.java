package prod_consumer;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/17 10:53
 */
public class Test {
    public static void main(String[] args) {
        EventStorage storage = new EventStorage();
        Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);
        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);
        thread2.start();
        thread1.start();
    }
}
