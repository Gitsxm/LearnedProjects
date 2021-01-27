package phaser.exam;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/24 23:49
 */
public class Test {
    public static void main(String[] args) {
        MyPhaser myPhaser = new MyPhaser();
        Student[] students = new Student[5];
        for (int i = 0; i < 5; i++) {
            students[i] = new Student(myPhaser);
            myPhaser.register();
        }

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(students[i], "student" + i);
            threads[i].start();
        }
        for (int i = 0; i < 5; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
