package canceltask;

import java.util.concurrent.Callable;

/**
 * TODO 执行器取消线程任务
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/7 20:38
 */
public class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        while (true){
            System.out.println("Task: test .....");
            Thread.sleep(500);
        }
    }
}
