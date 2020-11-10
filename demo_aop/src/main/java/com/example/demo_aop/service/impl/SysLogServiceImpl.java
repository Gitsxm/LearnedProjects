package com.example.demo_aop.service.impl;

import com.example.demo_aop.domain.SysLogEntity;
import com.example.demo_aop.repository.SysLogEntityMapper;
import com.example.demo_aop.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 日志服务
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/10 18:28
 */
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogEntityMapper mapper;

    /**
     * 日志插入
     * @param entity
     */
    public void insert(SysLogEntity entity){
        mapper.insertSelective(entity);
    }
}
