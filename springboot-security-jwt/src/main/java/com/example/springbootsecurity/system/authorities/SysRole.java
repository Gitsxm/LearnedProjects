package com.example.springbootsecurity.system.authorities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NegativeOrZero;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class SysRole implements Serializable {
    private static final long serialVersionUID = 5135773558049295164L;
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
