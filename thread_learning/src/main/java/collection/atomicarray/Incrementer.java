package collection.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/23 23:59
 */
public class Incrementer implements Runnable{
    private AtomicIntegerArray vector;

    public Incrementer(AtomicIntegerArray vector) {
        this.vector = vector;
    }

    @Override
    public void run() {
        for (int i=0;i<vector.length();i++){
            vector.getAndIncrement(i);
        }
    }
}
