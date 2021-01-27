package countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 等待多个并发完成--视频会议线程类
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/22 15:02
 */
public class Videoconference implements Runnable{
    private final CountDownLatch countDownLatch;

    public Videoconference(int number) {
        this.countDownLatch = new CountDownLatch(number);
    }

    @Override
    public void run() {
        System.out.println("Videoconference initialization "+countDownLatch.getCount()+" participants");
        try {
            countDownLatch.await();
            System.out.println("All the participants came in!");
            System.out.println("Conference start...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void arrive(String name){
        System.out.println(name+" come in...");
        countDownLatch.countDown();
        System.out.println("Videoconference waiting for "+countDownLatch.getCount()+" participate");

    }
}
