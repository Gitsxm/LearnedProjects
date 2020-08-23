package com.example.springbootcache.controller;

import com.example.springbootcache.domain.Person;
import com.example.springbootcache.repository.PersonDAO;
import com.example.springbootcache.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonServiceImpl service;

    @Autowired
    private PersonDAO dao;

    @PostMapping("/save")
    public void save(Person person){
        service.saveOne(person);
    }

    @GetMapping("/findById")
    public Optional<Person> findOne(Person person){
        return service.findOne(person);
    }

    @DeleteMapping("/remove")
    public void remove(Integer id){
        service.remove(id);
    }


    @GetMapping("/findInRedis")
    public Person findInRedis(Person person){
        return dao.getPerson(person);
    }

    @PostMapping("/saveToRedis")
    public void addToRedis(Person person){
        dao.save(person);
    }

    @GetMapping("/setStr")
    public void setString(String key, String value){
        dao.stringRedisTemplate(key,value);
    }

    @GetMapping("/getStr")
    public String getString(String key){
        return dao.getString(key);
    }

}
