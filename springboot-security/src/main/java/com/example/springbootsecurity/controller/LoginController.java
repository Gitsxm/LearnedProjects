package com.example.springbootsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/home",method = RequestMethod.POST)
    public String home(){
        return "home";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "login";
    }

    @RequestMapping("/deny")
    @ResponseBody
    public String deny(){
        return "权限不足！";
    }
}
