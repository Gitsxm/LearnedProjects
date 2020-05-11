package com.example.demo_redisson.service;

import com.example.demo_redisson.domain.User;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author MGG
 * @Date 2020/3/29 11:53
 * @Version 1.0
 */
@Service
public class UserService {

    /**
     * 验证用户
     * @param id
     * @return
     */
    public User findUserById(String id){
        User user = new User("mgg","1234","1001");
        return user;
    }

    /**
     * 用户注销
     * @param user
     */
    public boolean logOut(User user){

        return true;
    }
}
