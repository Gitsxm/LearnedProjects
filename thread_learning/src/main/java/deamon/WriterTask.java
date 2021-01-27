package deamon;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * 守护线程  写入线程
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/14 17:11
 */
public class WriterTask implements Runnable{
    private Deque<Event> dequel;

    public WriterTask(Deque<Event> dequel) {
        this.dequel = dequel;
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            Event event = new Event();
            event.setTime(new Date());
            event.setEvent("The thread "+Thread.currentThread().getId()+" has generated an event");
            dequel.addFirst(event);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
