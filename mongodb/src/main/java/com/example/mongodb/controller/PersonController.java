package com.example.mongodb.controller;

import com.example.mongodb.entity.Person;
import com.example.mongodb.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonServiceImpl service;

    @GetMapping("/findById")
    public Person findById(String id){
        return service.findById(id);
    }

    @GetMapping("/findAll")
    public List<Person> findAll(){
        return service.findAll();
    }

    @PostMapping("/save")
    public Person save(Person person){
        return service.savePerson(person);
    }
    @PutMapping("/update")
    public long update(Person person){
        return service.updatePerson(person);
    }

    @DeleteMapping("/del")
    public long delete(String id){
        return service.deletePerson(id);
    }
}
