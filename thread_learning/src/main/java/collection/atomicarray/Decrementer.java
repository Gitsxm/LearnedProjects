package collection.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/24 0:02
 */
public class Decrementer implements Runnable{
    private AtomicIntegerArray vector;

    public Decrementer(AtomicIntegerArray vector) {
        this.vector = vector;
    }

    @Override
    public void run() {
        for (int i=0;i<vector.length();i++){
            vector.getAndDecrement(i);
        }
    }
}
