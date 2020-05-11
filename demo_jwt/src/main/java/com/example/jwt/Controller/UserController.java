package com.example.jwt.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.jwt.annotations.PassToken;
import com.example.jwt.domain.User;
import com.example.jwt.service.UserService;
import com.example.jwt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserController
 * @Description 用户接口
 * @Author MGG
 * @Date 2020/3/25 21:41
 * @Version 1.0
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PassToken
    @PostMapping("/login")
    public String userLogin(@RequestBody User user) {
        JSONObject json = new JSONObject();
        if (user == null) {
            json.put("msg", "用户登录信息不能为空！");
            return json.toString();
        }
        User userFromBase = userService.findUserById(user.getUserId());
        if (userFromBase == null) {
            json.put("msg", "为查询到相关用户信息，请先注册！");
            return json.toString();
        }
        if (!(userFromBase.getPassWord().equals(user.getPassWord()))) {
            json.put("msg", "输入密码错误");
            return json.toString();
        }
        //创建 token
        String token = JwtUtils.createToken(userFromBase);
        json.put("token", token);
        json.put("user", userFromBase);
        return json.toString();
    }

    /**
     * 测试接口
     *
     * @return
     */
    @GetMapping("/msg")
    public String getMessage() {
        return "已验证权限";
    }
}
