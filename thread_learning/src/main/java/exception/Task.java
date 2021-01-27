package exception;

/**
 * 测试非运行时异常线程
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/15 9:49
 */
public class Task implements Runnable{
    @Override
    public void run() {
        int num = Integer.parseInt("TTT");
    }
}
