package com.example.springbootsecurity.pwd;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PwdGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPwd = "mgg1234";
        //获取加密密码
        System.out.println(encoder.encode(rawPwd));
        String encodePwd = "$2a$10$LPqmxaE4quRT8umNQAqtIO5opAWakSjwgm9WpzDKMvn.UVkwWmrZC";
        //密码匹配方法
        System.out.println(encoder.matches(rawPwd,encodePwd));
    }
}
