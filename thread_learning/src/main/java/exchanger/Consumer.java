package exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * TODO exchanger 数据交换-消费者问题-消费者
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/28 23:01
 */
public class Consumer implements Runnable{
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle = 1;
        for (int i=0;i<10;i++){
            System.out.println("Consumer: Cycle"+cycle);

            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Consumer: "+buffer.size());
            for (int j=0;j<10;j++){
                String event = "Event "+buffer.get(0);
                System.out.println("Consumer: "+event);
                buffer.remove(0);
            }
            cycle++;
        }
    }
}
