package com.example.springbootlearning.controller;

import com.example.springbootlearning.domain.Dog;
import com.example.springbootlearning.domain.Person;
import com.example.springbootlearning.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequestMapping("/dog")
@RestController
public class DogController {

    @Autowired
    private DogRepository dogRepository;

    @GetMapping("/findById/{id}")
    public Optional<Dog> findDogById(@PathVariable Integer id){
        return  dogRepository.findById(id);
    }

    @GetMapping("/findDogByNameAndAge")
    public List<Dog> findDogByNameAndAge(String name, Integer age){
        return dogRepository.findByNameAndAge(name,age);
    }

    @GetMapping("/findAll")
    public List<Dog> list(){
        return dogRepository.list();
    }

    @PostMapping("/save")
    public Dog add(@RequestBody Dog dog){
        return dogRepository.save(dog);
    }

    @PostMapping("/saveAll")
    public List<Dog> addAll(@RequestBody List<Dog> dogs){
        return dogRepository.saveAll(dogs);
    }

    /**
     * 查找排序
     * @return
     */
    @GetMapping("/sort")
    public List<Dog> findAll(){
        return dogRepository.findAll(Sort.by(Sort.Direction.DESC,"age"));
    }

    /**
     * 分页查找
     * @return
     */
    @GetMapping("/page")
    public Page<Dog> findAllByPage(){
        return dogRepository.findAll(PageRequest.of(1,2));
    }

}
