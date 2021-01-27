package forkjoin.throwexception;

import forkjoin.canceltask.TaskManager;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * TODO 再任务中抛出异常
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/15 18:56
 */
public class Task extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 4142494439277630392L;

    private int[] array;
    private int start,end;
    private TaskManager taskManager;

    public Task(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    public Task(int[] array, int start, int end, TaskManager taskManager) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.taskManager = taskManager;
    }

    @Override
    protected Integer compute() {
        System.out.println("Task: Start from "+start+" to "+end+" ....");
        if (end-start<10){
            if (3>start && 3<end){
                //throw new RuntimeException("Task: Throw exception: task from "+start+" to "+end+" ....");
                //也可以这么写
                RuntimeException exception = new RuntimeException("Task: Throw exception: task from "+start+" to "+end+" ....");
                completeExceptionally(exception);

            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            int mid = (end+start)/2;
            Task task1 = new Task(array,start,mid);
            Task task2 = new Task(array,mid,start);
            invokeAll(task1,task2);
        }
        System.out.println("Task: End from "+start+" to "+end+"...");
        return 0;
    }
}
