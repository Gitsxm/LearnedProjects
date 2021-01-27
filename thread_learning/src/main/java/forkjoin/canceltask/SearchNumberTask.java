package forkjoin.canceltask;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * TODO 寻找整租数据的数字
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/17 11:15
 */
public class SearchNumberTask extends RecursiveTask<Integer> {
    private int[] numbers;
    private int start, end;
    private int number;
    private TaskManager manager;

    public SearchNumberTask(int[] numbers, int start, int end, int number, TaskManager manager) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.number = number;
        this.manager = manager;
    }

    @Override
    protected Integer compute() {
        System.out.println("Task: " + start + "--" + end);
        int ret = 0;
        if (end - start > 10) {
            ret = launchTasks();
        } else {
            ret = lookForNumber();
        }
        return ret;
    }

    private int lookForNumber() {
        for (int i = start; i < end; i++) {
            if (numbers[i] == number) {
                System.out.println("Task： Number " + numbers[i] + " found in position " + i);
                manager.cancelTask(this);
                return i;
            }
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int launchTasks() {
        int mid= (start+end)/2;
        SearchNumberTask task1 = new SearchNumberTask(numbers,start,mid,number,manager);
        SearchNumberTask task2 = new SearchNumberTask(numbers,mid,end,number,manager);
        manager.addTask(task1);
        manager.addTask(task2);
        task1.fork();
        task2.fork();
        int ret =0;
        ret = task1.join();
        if (ret !=-1){
            return ret;
        }
        ret = task2.join();
        return ret;
    }

    public void writeCancelMsg() {
        System.out.println("Task: Cancel task from "+start+" to "+end+" ...");
    }
}
