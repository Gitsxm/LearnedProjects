package com.example.druid.service.impl;

import com.example.druid.domain.Dog;
import com.example.druid.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogServiceImpl {
    @Autowired
    private DogRepository repository;

    public List<Dog> findAll(){
        return repository.findAll();
    }

    public void save(Dog dog){
        repository.save(dog);
    }
}
