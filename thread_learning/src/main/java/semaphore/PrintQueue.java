package semaphore;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 信号量示例，打印队列。
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/22 10:37
 */
public class PrintQueue {
    private final Semaphore semaphore;
    private boolean[] freePrinters;
    private Lock printerLock;

    public PrintQueue() {
        this.semaphore = new Semaphore(3);
        //公平模式
//        this.semaphore = new Semaphore(1,true);
        freePrinters = new boolean[3];
        for (int i=0;i<3;i++){
            freePrinters[i] = true;
        }
        printerLock = new ReentrantLock();
    }

    /**
     * 打印方法
     * @param doc
     */
    public void printJob(String doc){
        try {
            semaphore.acquire();
            int assignedPrinter = getPrinter();
            long duration = (long) (Math.random()*10);
            System.out.println(Thread.currentThread().getName()+" PrintQueue: print job in printer"+assignedPrinter+" duration "+duration+"s");
            Thread.sleep(duration*1000);
            freePrinters[assignedPrinter] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }

    }

    /**
     * 获取执行任务的打印机编号
     * @return
     */
    private int getPrinter(){
        int ret = -1;
        try{
            printerLock.lock();
            for (int i=0;i<freePrinters.length;i++){
                if (freePrinters[i]){
                    ret = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            printerLock.unlock();
        }
        return ret;
    }
}
