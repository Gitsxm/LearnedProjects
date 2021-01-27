package delaytask;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

/**
 * TODO 在执行器中延时执行任务
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/7 19:49
 */
public class Task implements Callable<String> {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.println(name+": Starting at .."+LocalDateTime.now());
        return "Hello world!!!";
    }
}
