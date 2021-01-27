package lock;

/**
 * 打印工作类
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/17 23:23
 */
public class Job implements Runnable{

    private PrintQueue queue;

    public Job(PrintQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+": Print a document....");
        queue.printJob(new Object());
        System.out.println(Thread.currentThread().getName()+": Print finished!");
    }
}
