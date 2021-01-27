package exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * TODO exchanger 数据交换示例-消费者问题-生产者
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/28 22:53
 */
public class Producer implements Runnable{
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle = 1;
        for (int i=0;i<10;i++){
            System.out.println("Producer: Cycle"+cycle);
            for (int j=0;j<10;j++){
                String event = "Event "+(i*10+j);
                System.out.println("Producer: "+event);
                buffer.add(event);
            }
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Producer: "+buffer.size());
            cycle++;
        }
    }
}
