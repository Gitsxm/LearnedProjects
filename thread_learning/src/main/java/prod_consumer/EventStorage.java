package prod_consumer;

import java.util.Date;
import java.util.LinkedList;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/17 10:44
 */
public class EventStorage {

    private int maxSize;
    private LinkedList<Date> storage;

    public EventStorage(int maxSize, LinkedList storage) {
        this.maxSize = maxSize;
        this.storage = storage;
    }

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<>();
    }

    public synchronized void set() {
        while (storage.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.println("Set :" + storage.size());
        notifyAll();
    }

    public synchronized void get() {
        while (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Get :" + storage.size() + " :" + storage.poll());
        notifyAll();
    }
}
