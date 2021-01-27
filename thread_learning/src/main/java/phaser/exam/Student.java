package phaser.exam;

import java.util.Date;
import java.util.concurrent.Phaser;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/24 23:41
 */
public class Student implements Runnable{

    private Phaser phaser;

    public Student(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" : has arrived... "+new Date().getTime());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName()+" : do first exercise... "+new Date().getTime());
        doFirst();
        System.out.println(Thread.currentThread().getName()+" : first done... "+new Date().getTime());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName()+" : do second exercise... "+new Date().getTime());
        doSecond();
        System.out.println(Thread.currentThread().getName()+" : second done... "+new Date().getTime());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName()+" : do third exercise... "+new Date().getTime());
        doThird();
        System.out.println(Thread.currentThread().getName()+" : third done... "+new Date().getTime());
        phaser.arriveAndAwaitAdvance();
    }

    private void doFirst(){
        int duration = (int) (Math.random()*10);
        try {
            Thread.sleep(duration*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doSecond(){
        int duration = (int) (Math.random()*10);
        try {
            Thread.sleep(duration*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doThird(){
        int duration = (int) (Math.random()*10);
        try {
            Thread.sleep(duration*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
