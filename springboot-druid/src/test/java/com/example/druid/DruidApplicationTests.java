package com.example.druid;

import com.example.druid.domain.Dog;
import com.example.druid.service.impl.DogServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DruidApplicationTests {
    @Autowired
    private DogServiceImpl service;

    @Test
    void contextLoads() {
        Dog dog1 = new Dog();
        dog1.setName("huahua");
        dog1.setAge(2);
        dog1.setType("JM");
        Dog dog2 = new Dog();
        dog2.setName("wangwang");
        dog2.setAge(1);
        dog2.setType("HSQ");
        service.save(dog1);
        service.save(dog2);
        List<Dog> dogs = service.findAll();
        for (Dog dog : dogs) {
            System.out.println(dog);
        }
    }

}
