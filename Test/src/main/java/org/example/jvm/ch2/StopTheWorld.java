package org.example.jvm.ch2;

import java.util.HashMap;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/8 8:54
 */
public class StopTheWorld {
    public static class MyThread extends Thread{
        HashMap map = new HashMap();

        @Override
        public void run() {
            try{
                while (true){
                    if (map.size()*1024/1024/1024>=900){
                        map.clear();
                        System.out.println("map clear");
                    }
                    byte[] b1 ;
                    for(int i=0;i<100;i++){
                        b1 = new byte[1024];
                        map.put(System.nanoTime(),b1);
                    }
                    Thread.sleep(1);
                }
            }catch (Exception e){

            }
        }
    }

    public static class PrintThread extends Thread{
        public static final long START_TIME = System.currentTimeMillis();
        @Override
        public void run() {
            try{
                while (true){
                    long t = System.currentTimeMillis()-START_TIME;
                    System.out.println(t/1000+"."+t%1000);
                    Thread.sleep(1);
                }
            }catch (Exception e){

            }
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        PrintThread printThread = new PrintThread();
        thread.start();
        printThread.start();
    }

}
