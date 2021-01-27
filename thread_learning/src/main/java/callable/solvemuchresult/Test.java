package callable.solvemuchresult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * TODO 发送任务列表给执行器，通过 invokeAll 等待所有任务都完成。使用 Future 对象来接收结果。
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/7 19:26
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Task> taskList = new ArrayList<>();
        for (int i=0;i<3;i++){
            Task task = new Task(i+"");
            taskList.add(task);
        }
        List<Future<Result>> futureList = null;
        try {
            futureList = executorService.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("Main:Printing results:");
        for (Future<Result> future:futureList){
            try {
                Result result = future.get();
                System.out.println(result.getName()+": "+result.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
