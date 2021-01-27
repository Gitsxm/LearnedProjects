package callable.controltaskfinish;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * TODO 在执行器中控制任务的完成-任务线程
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/8 18:49
 */
public class ExecutableTask implements Callable<String> {
    private String name;

    public String getName() {
        return name;
    }

    public ExecutableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        int duration = (int) (Math.random()*10);
        System.out.println(name+": Waiting "+duration+" seconds for results...");
        TimeUnit.SECONDS.sleep(duration);
        return "Hello world.I am "+name;
    }
}
