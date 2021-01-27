package cycletask;

import java.time.LocalDateTime;

/**
 * TODO 周期性执行任务 Callable 会返回结果是不行的
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/7 20:04
 */
public class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name+": Starting at "+ LocalDateTime.now());
    }
}
