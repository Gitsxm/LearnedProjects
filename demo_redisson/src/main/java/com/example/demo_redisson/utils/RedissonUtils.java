package com.example.demo_redisson.utils;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;

/**
 * @ClassName RedissonUtils
 * @Description TODO
 * @Author MGG
 * @Date 2020/3/29 12:49
 * @Version 1.0
 */
public class RedissonUtils {
    RedissonClient client = Redisson.create();

}
