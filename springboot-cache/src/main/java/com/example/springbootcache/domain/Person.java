package com.example.springbootcache.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person implements Serializable {
    private static final long serialVersionUID = -3250464354467547606L;

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
}
