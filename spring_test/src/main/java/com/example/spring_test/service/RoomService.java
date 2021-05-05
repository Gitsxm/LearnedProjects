package com.example.spring_test.service;

import com.example.spring_test.task.RoomTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private UserService service;

    public String personDo() throws InterruptedException {
        service.eat();
        RoomTask task1 = new RoomTask(service,2000);
        RoomTask task2 = new RoomTask(service,5000);
        task1.start();
        task2.start();
        return "1";
    }
}
