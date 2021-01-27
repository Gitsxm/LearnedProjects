package customerpoolexecutor.mythreadfactory;

/**
 * TODO 使用定制线程工厂创建定制线程
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/25 9:22
 */
public class Test {
    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
        MyTask task = new MyTask();
        Thread thread = factory.newThread(task);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main : Thread info....");
        System.out.println(thread);
        System.out.println("Main : finished.....");
    }
}
