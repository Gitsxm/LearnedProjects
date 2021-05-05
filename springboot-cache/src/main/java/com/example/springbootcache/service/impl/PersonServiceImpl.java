package com.example.springbootcache.service.impl;

import com.example.springbootcache.domain.Person;
import com.example.springbootcache.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl {

    @Autowired
    private PersonRepository repository;

    @CachePut(value = "people", key = "#person.id") // 缓存新增或者更新缓存，缓存名为people，key 为person 的 id 属性
    public Person saveOne(Person person) {
        Person p = repository.save(person);
        System.out.println("保存 person.id = " + person.getId() + " 的数据到缓存 people 中，key = " + person.getId());
        // 必须有返回保存的 person 对象
        return p;
    }

    @Cacheable(value = "people", key = "#person.id") // 缓存 key = person id 属性的值的数据到 people 中
    public Optional<Person> findOne(Person person) {
        System.out.println("保存 person.id = " + person.getId() + " 的数据到缓存 people 中，key = " + person.getId());
        return repository.findById(person.getId());

    }

    @CacheEvict(value = "people") // 从缓存 people 中删除 key = person.id 的数据
    public void remove(Integer id) {
        repository.deleteById(id);
        System.out.println("从缓存 people 中删除 key = " + id + " 的数据");
    }
}
