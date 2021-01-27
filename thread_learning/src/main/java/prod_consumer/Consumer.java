package prod_consumer;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/17 10:52
 */
public class Consumer implements Runnable {
    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.get();
        }
    }
}
