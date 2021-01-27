package semaphore;

/**
 * 信号量测试，打印工作线程,多台打印机工作。
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/22 10:43
 */
public class Job implements Runnable{
    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" printing.......");
        printQueue.printJob("doc.....");
        System.out.println(Thread.currentThread().getName()+" print finished!");
    }
}
