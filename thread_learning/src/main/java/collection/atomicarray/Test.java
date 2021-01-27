package collection.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/24 0:04
 */
public class Test {
    public static void main(String[] args) {
        final int THREADS = 100;
        AtomicIntegerArray array = new AtomicIntegerArray(1000);
        Incrementer incrementer = new Incrementer(array);
        Decrementer decrementer = new Decrementer(array);
        Thread[] inthreads = new Thread[THREADS];
        Thread[] dethreads = new Thread[THREADS];
        for(int i=0;i<THREADS;i++){
            inthreads[i] = new Thread(incrementer);
            inthreads[i].start();
            dethreads[i] = new Thread(decrementer);
            dethreads[i].start();
        }
        for(int i=0;i<THREADS;i++){

            try {
                inthreads[i].join();
                dethreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<array.length();i++){
            if (array.get(i) !=0){
                System.out.printf("Vector[%d] : "+array.get(i),i);
            }
        }
        System.out.println("Main: finished!..");
    }
}
