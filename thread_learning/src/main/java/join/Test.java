package join;

import java.time.LocalDateTime;

/**
 * 测试 join 主线程需要等待 task1、2 结束才能执行结束。
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/14 15:43
 */
public class Test {
    public static void main(String[] args) {
        DataSourceLoader sourceLoader = new DataSourceLoader();
        NetWorkConnLoader connLoader = new NetWorkConnLoader();
        Thread task1 = new Thread(sourceLoader,"dataSourceLoader");
        Thread task2 = new Thread(connLoader,"connLoader");
        task1.start();
        task2.start();
        try {
            task1.join();
            task2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Configures load finished:"+ LocalDateTime.now());


    }
}
