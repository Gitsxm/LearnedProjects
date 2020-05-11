package com.example.demo_aop.repository;

import com.example.demo_aop.domain.Stuinfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StuinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Stuinfo record);

    int insertSelective(Stuinfo record);

    Stuinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stuinfo record);

    int updateByPrimaryKey(Stuinfo record);

    List<Stuinfo> seletcAll();
}
