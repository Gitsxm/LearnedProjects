package com.example.springbootlearning.controller;

import com.mgg.spring_boot_starter_hello.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/msg")
    public String getMsg(){
        return  helloService.sayHello();
    }
}
