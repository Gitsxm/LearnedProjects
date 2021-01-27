package sharedparams;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 共享参数安全问题-安全的成员变量
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/15 10:41
 */
public class SafeTask implements Runnable{
    private static ThreadLocal<Date> startDate = new ThreadLocal<Date>(){
        protected Date initialValue(){
            return new Date();
        }
    };

    /**
     * 经过测试 可以使用成员变量 private ThreadLocal<Date> startDate = new ThreadLocal<Date> ;， 然后再run 中 set(new Date) 效果一样的。
     */
    @Override
    public void run() {
        System.out.println("Start thread :"+Thread.currentThread().getId()+":"+startDate.get());
        try {
            TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread finished:"+Thread.currentThread().getId()+":"+startDate.get());
    }
}
