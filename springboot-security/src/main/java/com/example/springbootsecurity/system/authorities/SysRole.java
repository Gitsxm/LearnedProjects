package com.example.springbootsecurity.system.authorities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NegativeOrZero;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class SysRole {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
