package create;

/**
 * 继承 Thread 创建线程方法
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/6 16:24
 */
public class SimpleThread extends Thread{
    @Override
    public void run() {
        System.out.println("线程启动。。。");
    }
}
