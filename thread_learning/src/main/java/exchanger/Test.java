package exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/28 23:06
 */
public class Test {
    public static void main(String[] args) {
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();
        Exchanger<List<String>> exchanger = new Exchanger<>();
        Producer producer = new Producer(buffer1,exchanger);
        Consumer consumer = new Consumer(buffer2,exchanger);
        Thread tp = new Thread(producer);
        Thread tc = new Thread(consumer);
        tp.start();
        tc.start();
    }
}
