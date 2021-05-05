package com.example.spring_test.service;

import com.example.spring_test.task.RoomTask;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void sleep(int sec) throws InterruptedException {
        System.out.println("这个人睡了。。。");
        Thread.sleep(sec);
        System.out.println("醒了");
    }

    public void eat() throws InterruptedException {
        System.out.println("小明吃饭。。。");
        Thread.sleep(10000);
        System.out.println("小明吃饱了");
    }
}
