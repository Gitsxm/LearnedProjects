package deamon;

import java.util.Date;
import java.util.Deque;

/**
 * 守护线程 清理线程
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/14 17:18
 */
public class CleanerTask extends Thread{

    private Deque<Event> deque;

    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true){
            Date date = new Date();
            clean(date);
        }
    }

    private void clean(Date date) {
        long difference;
        boolean delete;
        if (deque.size()==0){
            return;
        }
        delete = false;
        do{
            Event event = deque.getLast();
            difference = date.getTime()- event.getTime().getTime();
            if (difference > 10000){
                System.out.println("Cleaner:"+event.getEvent());
                deque.removeLast();
                delete = true;
            }
        }while (difference>10000);

        if (delete){
            System.out.println("Cleaner:Size of the Deque:"+deque.size());
        }
    }
}
