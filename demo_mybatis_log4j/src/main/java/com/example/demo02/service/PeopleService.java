package com.example.demo02.service;

import com.example.demo02.domain.People;

import java.util.List;

/**
 * @ClassName People
 * @Description TODO
 * @Author MGG
 * @Date 2018/12/22 0022 12:05
 * @Version 1.0
 */

public interface PeopleService {
    List<People> findAll();

    List<People> findAllById(String id);

    int insertIntoTable(People people);
}
