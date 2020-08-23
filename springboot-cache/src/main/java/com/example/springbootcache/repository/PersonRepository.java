package com.example.springbootcache.repository;

import com.example.springbootcache.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person,Integer> {
    @Query("select p from Person p where id = :id ")
    Person findByIdBackPerson(@Param("id") Integer id);
}
