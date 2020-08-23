package com.example.springbootlearning.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "dog")
@NamedQuery(name = "Dog.list",query = "select d from dog d where d.id = '10001'")
public class Dog {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String type;
    private Integer age;
    private String gender;
}
