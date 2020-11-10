package com.example.demo_aop.repository;

import com.example.demo_aop.domain.SysLogEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SysLogEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLogEntity record);

    int insertSelective(SysLogEntity record);

    SysLogEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLogEntity record);

    int updateByPrimaryKey(SysLogEntity record);
}