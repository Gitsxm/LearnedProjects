package solverefusetask;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO 处理再执行器中被执行的任务-这边进行具体的处理哦
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/15 9:33
 */
public class RejectedTaskController implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("RejectedTaskController: The task "+r.toString()+" has been rejected...");
        System.out.println("RejectedTaskController: "+executor.toString());
        System.out.println("RejectedTaskController: Terminating: "+executor.isTerminating());
        System.out.println("RejectedTaskController: Terminated: "+executor.isTerminated());
    }
}
