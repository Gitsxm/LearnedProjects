package group;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/16 22:08
 */
public class SearchTask implements Runnable{
    private Result result;

    public SearchTask(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        String name  = Thread.currentThread().getName();
        System.out.println("Thread "+name+" start!");
        try {
            doTask();
            result.setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread "+name+" end!");
    }

    private void doTask() throws InterruptedException {
        Random random = new Random((new Date()).getTime());
        int value = random.nextInt()*100;
        System.out.println("Thread "+Thread.currentThread().getName()+" :"+value);
        TimeUnit.SECONDS.sleep(1);
    }
}
