package exception;

/**
 * 自定义线程非运行时异常处理类
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/15 9:44
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("An exception has been capture!");
        System.out.println("Thread:"+t.getId());
        System.out.println("Exception:"+e.getClass()+"\n"+e.getMessage());
        e.printStackTrace(System.out);
        System.out.println("Thread status:"+ t.getState());
    }
}
