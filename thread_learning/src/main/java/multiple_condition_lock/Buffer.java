package multiple_condition_lock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 缓冲区
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/20 21:32
 */
public class Buffer {
    private LinkedList<String> buffer;
    private int maxSize;
    private ReentrantLock lock;
    private Condition lines;
    private Condition space;
    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        space = lock.newCondition();
        pendingLines = true;
    }

    public void insert(String line) {
        lock.lock();
        try {
            while (buffer.size() == maxSize) {
                space.await();
            }
            buffer.offer(line);
            System.out.println(Thread.currentThread().getName() + " Inserted line : " + buffer.size());
            lines.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public String get(){
        String line = null;
        lock.lock();
        try{
            while((buffer.size()==0) && (hasPendingLines())){
                lines.await();
            }
            if (hasPendingLines()){
                line = buffer.poll();
                System.out.println(Thread.currentThread().getName()+" Line readed: "+buffer.size());
                space.signalAll();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return line;
    }

    public boolean hasPendingLines(){
        return this.pendingLines || buffer.size()>0;
    }

    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }
}
