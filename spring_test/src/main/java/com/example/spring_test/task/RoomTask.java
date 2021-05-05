package com.example.spring_test.task;

import com.example.spring_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoomTask extends Thread{

    private UserService service;
    private int sec;

    public RoomTask(UserService service,int sec) {
        this.service = service;
        this.sec = sec;
    }

    @Override
    public void run() {
        try {
            service.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
