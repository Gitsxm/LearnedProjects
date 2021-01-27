package phaser;

import java.util.concurrent.Phaser;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/23 17:25
 */
public class Test {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        FileSearch system = new FileSearch("c:\\Windows","log",phaser);
        FileSearch app = new FileSearch("c:\\Program Files","log",phaser);
        FileSearch doc = new FileSearch("d:\\","log",phaser);
        Thread threadS = new Thread(system,"system");
        Thread threadA = new Thread(app,"app");
        Thread threadD = new Thread(doc,"doc");
        threadA.start();
        threadD.start();
        threadS.start();
        try {
            threadA.join();
            threadD.join();
            threadS.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Terminated : "+phaser.isTerminated());
    }
}
