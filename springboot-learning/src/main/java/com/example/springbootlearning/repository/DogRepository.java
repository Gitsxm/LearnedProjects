package com.example.springbootlearning.repository;

import com.example.springbootlearning.domain.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

public interface DogRepository extends JpaRepository<Dog,Integer> {

    List<Dog> findByType(String type);

    List<Dog> findByName(String name);

    @Query("select d from dog d where 1=1 and d.name = :name and d.age = :age")
    List<Dog> findByNameAndAge(@Param("name") String name,@Param("age") Integer age);

    List<Dog> list();
}
