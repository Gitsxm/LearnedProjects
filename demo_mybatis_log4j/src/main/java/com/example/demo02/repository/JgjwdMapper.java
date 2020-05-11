package com.example.demo02.repository;

import com.example.demo02.domain.Jgjwd;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JgjwdMapper {
    int insert(Jgjwd record);

    int insertSelective(Jgjwd record);

    List<Jgjwd> findAll();
}
