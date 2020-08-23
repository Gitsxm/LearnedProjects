package com.example.druid.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Dog implements Serializable {
    private static final long serialVersionUID = 2108404465302027598L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    private int age;
}
