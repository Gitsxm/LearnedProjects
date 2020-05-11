package com.example.demo_redisson.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo_redisson.domain.User;
import com.example.demo_redisson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author MGG
 * @Date 2020/3/29 11:55
 * @Version 1.0
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestBody User user){
        JSONObject jsonObject = new JSONObject();
        User  baseUser = userService.findUserById(user.getId());
        if(baseUser == null){
            jsonObject.put("msg","用户名或者密码错误！");
            jsonObject.put("code","500");
            jsonObject.put("result",null);
            return jsonObject.toJSONString();
        }
        jsonObject.put("msg","success！");
        jsonObject.put("code","200");
        jsonObject.put("result",baseUser);
        return jsonObject.toJSONString();
    }

    /**
     * 注销
     * @return
     */
    @PostMapping("/logout")
    public String logOut(@RequestBody User user){
        JSONObject jsonObject = new JSONObject();
        userService.logOut(user);
        jsonObject.put("msg","注销成功！");
        jsonObject.put("code","200");
        jsonObject.put("result",null);
        return jsonObject.toJSONString();
    }
}
