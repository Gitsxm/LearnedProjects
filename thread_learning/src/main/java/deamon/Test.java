package deamon;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 守护线程测试
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/14 17:25
 */
public class Test {
    public static void main(String[] args) {
        Deque<Event> deque = new ArrayDeque<>();
        WriterTask task = new WriterTask(deque);
        for (int i=0;i<3;i++){
            Thread thread = new Thread(task);
            thread.start();
        }
        CleanerTask cleanerTask = new CleanerTask(deque);
        cleanerTask.start();
    }
}
