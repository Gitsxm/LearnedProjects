package com.example.springbootsecurity.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/new")
    public String add(){
        return "新增成功";
    }

    @GetMapping("/delete")
    public String delete(){
        return "/删除成功";
    }

    @GetMapping("/edit")
    public String update(){
        return "修改成功！";
    }
}
