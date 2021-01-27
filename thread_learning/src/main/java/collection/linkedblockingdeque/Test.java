package collection.linkedblockingdeque;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * TODO 创建了指定容量的 deque 当插满后会阻塞等有容量后继续插入
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/19 10:18
 */
public class Test {
    public static void main(String[] args) {
        LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>(3);
        Client client = new Client(deque);
        Thread thread = new Thread(client);
        thread.start();
        for (int i=0;i<5;i++){
            for (int j=0;j<3;j++){
                try {
                    String request = deque.take();
                    System.out.println("Main: Request "+request +" at "+ new Date() +". Size: "+deque.size());
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Main: End.....");
    }
}

