package com.example.demo_aop.service;

import com.example.demo_aop.domain.SysLogEntity;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/10 19:21
 */
public interface SysLogService {
    /**
     * 日志插入
     * @param entity
     */
    void insert(SysLogEntity entity);
}
