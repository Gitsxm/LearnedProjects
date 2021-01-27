package join;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 加载初始化数据示例
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/14 15:38
 */
public class DataSourceLoader implements Runnable{
    public void run() {
        System.out.println("Beginning data source loading:"+ LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Data source loading finished:"+LocalDateTime.now());
    }
}
