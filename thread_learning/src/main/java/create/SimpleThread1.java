package create;

/**
 * 继承 Runable 接口创建线程
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/6 16:26
 */
public class SimpleThread1 implements Runnable{
    private int number;

    public SimpleThread1(int number) {
        this.number = number;
    }

    public void run() {
        for (int i=1;i<=10;i++){
            System.out.printf("%s: %d * %d = %d\n",Thread.currentThread().getName(),number,i,i*number);
        }
    }
}
