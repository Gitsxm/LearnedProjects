package sharedparams;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 共享参数安全问题-不安全的成员变量
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/15 10:30
 */
public class UnsafeTask implements Runnable{
    private Date startDate;
    @Override
    public void run() {
        startDate = new Date();
        System.out.println("Start thread :"+Thread.currentThread().getId()+":"+startDate);
        try {
            TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread finished:"+Thread.currentThread().getId()+":"+startDate);
    }
}
