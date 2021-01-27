package join;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 初始化网络连接示例
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/14 15:42
 */
public class NetWorkConnLoader implements Runnable{
    @Override
    public void run() {
        System.out.println("Beginning conn:"+ LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Conn finished:"+LocalDateTime.now());
    }
}
