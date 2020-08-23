package com.example.mongodb.repository;

import com.example.mongodb.entity.Person;

import java.util.List;

public interface PersonRepository {
    Person savePerson(Person person);
    Person findById(String id);
    List<Person> findAll();
    long updatePerson(Person person);
    long deletePerson(String id);
}
