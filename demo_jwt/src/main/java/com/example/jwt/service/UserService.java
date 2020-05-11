package com.example.jwt.service;

import com.example.jwt.domain.User;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description 模拟用户业务类
 * @Author MGG
 * @Date 2020/3/25 21:40
 * @Version 1.0
 */
@Service
public class UserService {
    /**
     * 查找用户信息
     *
     * @param userId
     * @return
     */
    public User findUserById(String userId) {
        return new User(userId, "mgg", "1234");
    }
}
