package forkjoin.canceltask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * TODO 取消任务 存储执行中的任务
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/17 11:06
 */
public class TaskManager {
    private List<ForkJoinTask<Integer>> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(ForkJoinTask<Integer> task){
        tasks.add(task);
    }

    public void cancelTask(ForkJoinTask<Integer> ctask){
        tasks.stream().filter(e-> e != ctask).forEach(e->{
            e.cancel(true);
            ((SearchNumberTask)e).writeCancelMsg();
        });
    }
}
