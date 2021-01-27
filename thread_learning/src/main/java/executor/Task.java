package executor;

import java.util.Date;

/**
 * TODO 线程执行器-任务线程
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/29 22:52
 */
public class Task implements Runnable{
    private Date initDate;
    private String name;

    public Task(String name) {
        this.name = name;
        initDate = new Date();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Task "+name+" Create on : "+initDate);
        System.out.println(Thread.currentThread().getName()+" Task "+name+" Started on : "+new Date());
        try{
            int duration = (int) (Math.random()*10);
            System.out.println(Thread.currentThread().getName()+" Task "+name+" during "+duration+" s.");
            Thread.sleep(duration*1000);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" Task "+name+" finished on : "+new Date());
    }
}
