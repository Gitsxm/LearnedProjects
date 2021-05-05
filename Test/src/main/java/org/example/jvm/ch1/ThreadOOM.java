package org.example.jvm.ch1;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/19 23:40
 */
public class ThreadOOM {
    public static void main(String[] args) {
        for (int i=0;i<300000;i++){
            new Thread(new Mythread(),"Thread:"+i).start();
            System.out.println("CreateThread:"+i);
        }
    }

    static class Mythread implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
