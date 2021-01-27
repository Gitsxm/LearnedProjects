package exception;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/15 9:50
 */
public class Test {
    public static void main(String[] args) {
        Task task = new Task();
        Thread thread = new Thread(task);
        //将为所有线程设置
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        //单个线程设置
//        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }
}
