package executor.executorext;

/**
 * TODO 扩展线程执行器
 *
 * @author MGG
 * @version 1.0
 * @date 2021-03-13 4:09 PM
 */
public class MyTask implements Runnable{
    private String name;

    public MyTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Task: " + Thread.currentThread().getId()+"--"+name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
