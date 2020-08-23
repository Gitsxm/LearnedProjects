package com.example.springbootlearning.controller;

import com.example.springbootlearning.domain.Person;
import com.example.springbootlearning.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository repository;
    @PostMapping("/saveAll")
    public List<Person> addAllPerson(@RequestBody List<Person> persons){
        return repository.saveAll(persons);
    }
}
