package executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO 线程执行器-服务器
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/29 23:00
 */
public class Server {
    private ThreadPoolExecutor executor;

    public Server() {
        //创建了普通可缓存执行器
//        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        //创建了固定大小的执行器
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }

    public void executeTask(Task task){
        System.out.println("Server : A new task has arrived !");
        executor.execute(task);
        System.out.println("Server : Pool size :"+executor.getPoolSize());
        System.out.println("Server : Active count :"+executor.getActiveCount());
        System.out.println("Server : Completed tasks count:"+executor.getCompletedTaskCount());
        System.out.println("Server : Tasks count:"+executor.getTaskCount());
    }

    public void endServer(){
        executor.shutdown();
    }

}
