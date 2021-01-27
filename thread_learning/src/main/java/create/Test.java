package create;

import java.util.stream.IntStream;

/**
 * 两种实例方法
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/6 16:27
 */
public class Test {
    public static void main(String[] args) {
        //继承Thread类
        SimpleThread simpleThread = new SimpleThread();
        simpleThread.start();
        //实现 Runable 接口
        for (int i=0;i<=10;i++){
            Thread thread = new Thread(new SimpleThread1(i));
            thread.start();
        }
        //在Java8中还可以这样创建
        System.out.println("Stream create...");
        IntStream.range(0,5).forEach(e->new Thread(()->{
            System.out.println(Thread.currentThread().getId());
        }).start());
    }
}
