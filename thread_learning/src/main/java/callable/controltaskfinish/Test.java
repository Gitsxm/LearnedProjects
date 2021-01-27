package callable.controltaskfinish;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TODO 当线程任务执行完毕，执行 done 里的内容，可以用来关闭系统资源、输出日志信息、发送通知等。
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/8 18:59
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ResultTask[] tasks = new ResultTask[5];
        for (int i=0;i<5;i++){
            ExecutableTask executableTask = new ExecutableTask("Task"+i);
            tasks[i] = new ResultTask(executableTask);
            executorService.submit(tasks[i]);
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i=0;i<tasks.length;i++){
            tasks[i].cancel(true);
        }
        for (int i=0;i<tasks.length;i++){
            if (tasks[i].isCancelled()){
                try {
                    System.out.println(tasks[i].get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        executorService.shutdown();
    }
}
