package customerpoolexecutor.mypriorityexecutor;

import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * TODO 实现基于优先级的 Executor 类
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 8:51
 */
public class MyPriorityTask implements Runnable,Comparable<MyPriorityTask>{
    private int priority;
    private String name;

    public MyPriorityTask(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(MyPriorityTask o) {
        if (getPriority() < o.getPriority()){
            return 1;
        }
        if (getPriority() < o.getPriority()){
            return -1;
        }
        return 0;
    }

    @Override
    public void run() {
        System.out.println("MyPriorityTask: "+name+" priority : "+ priority);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
