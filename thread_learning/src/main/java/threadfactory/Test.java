package threadfactory;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/16 22:47
 */
public class Test {
    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory("myThreadFactory");
        Task task = new Task();
        Thread thread;
        for (int i=0;i<10;i++){
            thread = factory.newThread(task);
            thread.start();
        }
        System.out.println(factory.getStats());
    }
}
