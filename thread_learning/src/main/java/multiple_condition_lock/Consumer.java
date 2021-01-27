package multiple_condition_lock;

import java.util.Random;

/**
 * 读缓冲区
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/20 22:01
 */
public class Consumer implements Runnable{
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (buffer.hasPendingLines()){
            String line = buffer.get();
            processLine(line);
        }
    }

    /**
     * 模拟对数据处理
     * @param line
     */
    private void processLine(String line){
        try {
            Random random = new Random();
            System.out.println(Thread.currentThread().getName()+" process line:"+line);
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
