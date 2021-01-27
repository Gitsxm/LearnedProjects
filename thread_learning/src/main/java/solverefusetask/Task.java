package solverefusetask;

import java.util.concurrent.TimeUnit;

/**
 * TODO 处理再执行器中被拒绝的任务-开启一个打印线程
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/15 9:59
 */
public class Task implements Runnable{
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Task "+name+": Starting...");
        long duration = (long) (Math.random()*10);
        System.out.println("Task "+name+" ReprotGenerator: Generating a report during "+duration+" sections...");
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task "+name+" : Ending....");
    }

    @Override
    public String toString() {
        return name;
    }
}
