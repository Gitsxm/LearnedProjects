package com.example.demo_redisson.domain;

import lombok.*;

/**
 * @ClassName User
 * @Description TODO
 * @Author MGG
 * @Date 2020/3/29 11:52
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String username;
    private String password;
    private String id;
}
