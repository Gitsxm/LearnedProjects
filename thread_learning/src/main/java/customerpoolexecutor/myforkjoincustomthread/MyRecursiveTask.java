package customerpoolexecutor.myforkjoincustomthread;

import forkjoin.throwexception.Task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * TODO 通过 ThreadFactory 为 fork/join 生成定制线程 --task
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 22:53
 */
public class MyRecursiveTask extends RecursiveTask<Integer> {
    private int[] arr;
    private int start,end;

    public MyRecursiveTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        //通过强制类型转换转一下 具体什么逻辑操作书上很迷惑 代码给错了应该 这边应该要写分治代码
        MyWorkThread thread = (MyWorkThread) Thread.currentThread();
        thread.addTask();
        return null;
    }

    /**
     * 没什么用
     * @param task1
     * @param task2
     * @return
     */
    private Integer addResult(Task task1,Task task2){
        int value = 0;
        try {
            value = task1.get().intValue()+task2.get().intValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
            value = 0;
        } catch (ExecutionException e) {
            e.printStackTrace();
            value = 0;
        }
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }
}
