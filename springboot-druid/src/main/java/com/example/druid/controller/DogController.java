package com.example.druid.controller;

import com.example.druid.domain.Dog;
import com.example.druid.service.impl.DogServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DogController {
    @Autowired
    private DogServiceImpl service;

    Logger logger = LoggerFactory.getLogger(DogController.class);
    @GetMapping("/findAll")
    public void finAll(){
        List<Dog> dogs = service.findAll();
        for (Dog dog :
                dogs) {
            System.out.println(dog);
            logger.info("查询出 dog "+dog);
        }
    }


}
