package com.example.demo02.repository;

import com.example.demo02.domain.People;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleMapper {
    int deleteByPrimaryKey(String id);

    int insert(People record);

    int insertSelective(People record);

    List<People> selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKey(People record);

    List<People> findAll();
}