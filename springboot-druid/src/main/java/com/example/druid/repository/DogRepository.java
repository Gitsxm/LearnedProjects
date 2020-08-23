package com.example.druid.repository;

import com.example.druid.domain.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Integer> {
}
