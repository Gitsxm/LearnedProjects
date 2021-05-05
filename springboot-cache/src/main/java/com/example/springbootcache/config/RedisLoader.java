package com.example.springbootcache.config;

import com.example.springbootcache.domain.Person;
import com.example.springbootcache.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * TODO 加载 person 数据
 *
 * @author MGG
 * @version 1.0
 * @date 2021-03-18 10:01 AM
 */
@Component
public class RedisLoader implements CommandLineRunner {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private PersonRepository repository;

    @Override
    public void run(String... args) throws Exception {
        //清空
        redisTemplate.opsForList().leftPop("persons");
        System.out.println("加载 person 数据>>>>>>>>>>>>>>>>>>>>");
        List<Person> persons = repository.findAll();
        redisTemplate.opsForList().rightPush("persons", persons);
        System.out.println("加载 person 数据完成...");
    }
}
