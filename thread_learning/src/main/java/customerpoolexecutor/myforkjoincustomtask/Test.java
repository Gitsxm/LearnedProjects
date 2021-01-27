package customerpoolexecutor.myforkjoincustomtask;

import java.util.concurrent.ForkJoinPool;

/**
 * TODO 对元素加1 的任务 我们通过继承 forkjointask 定制了一个task类
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 23:44
 */
public class Test {
    public static void main(String[] args) {
        int[] arr = new int[10000];
        ForkJoinPool pool = new ForkJoinPool();
        Task task = new Task("Task",arr,0,10000);
        pool.invoke(task);
        pool.shutdown();
        System.out.println("Main: finished....");
    }
}
