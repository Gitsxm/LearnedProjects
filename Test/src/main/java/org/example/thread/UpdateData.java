package org.example.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UpdateData extends Thread {
    Logger logger = LoggerFactory.getLogger(UpdateData.class);

    public void updateWaterData() throws InterruptedException {
        System.out.println("更新数据开始...");
        Thread.sleep(10000);
        System.out.println("更新数据结束");
    }

    @Override
    public void run() {
        try {
            updateWaterData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
