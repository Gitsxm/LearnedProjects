package com.example.druid.mapper;

import com.example.druid.domain.FrReportheader;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface FrReportheaderMapper {
    int deleteByPrimaryKey(String id);

    int insert(FrReportheader record);

    int insertSelective(FrReportheader record);

    FrReportheader selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FrReportheader record);

    int updateByPrimaryKey(FrReportheader record);

    List<FrReportheader> exportData(@Param("beginTime") String beginTime, @Param("endTime") String endTime);
}