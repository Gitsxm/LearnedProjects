package com.example.springbootsecurity.controller;

import com.example.springbootsecurity.domain.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
//    @RequestMapping("/")
    public String index(Model model){
        Msg msg = new Msg("标题","睡觉翻跟头","额外信息,管理员可见");
        model.addAttribute("msg",msg);
        return "home";
    }
}
