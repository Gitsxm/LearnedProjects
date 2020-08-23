package com.example.springbootcache.repository;

import com.example.springbootcache.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class PersonDAO {
    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String,String> valueOpsStr;
    @Resource(name = "redisTemplate")
    private ValueOperations<Object,Object> valOps;

    public void stringRedisTemplate(String key,String value){
        valueOpsStr.set(key,value);
    }

    public void save(Person person){
        System.out.println(person.getId().toString());
        valOps.set(person.getId().toString(),person);
    }

    public String getString(String key){
        return valueOpsStr.get(key);
    }

    public Person getPerson(Person person){
        return (Person) valOps.get(person.getId().toString());
    }
}
