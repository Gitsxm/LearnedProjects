package com.example.demo_springdatarest.repository;

import com.example.demo_springdatarest.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(isolation = Isolation.READ_COMMITTED)
public interface PersonRepository extends JpaRepository<Person,Long> {
    @RestResource(path = "nameStartWith",rel = "nameStartWith")
    Person findByNameStartingWith(@Param("name") String name);
}
