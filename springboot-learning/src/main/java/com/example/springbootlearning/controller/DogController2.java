package com.example.springbootlearning.controller;

import com.example.springbootlearning.domain.Dog;
import com.example.springbootlearning.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DogController2 {
    @Autowired
    private DogService service;

    @PostMapping("/addwithback")
    public void tranAdd(Dog dog) {
        service.addWithRollBack(dog);
    }

    @PostMapping("/addwithoutback")
    public void tranAdd2(Dog dog) {
        service.addWithOutRollBack(dog);
    }
}
