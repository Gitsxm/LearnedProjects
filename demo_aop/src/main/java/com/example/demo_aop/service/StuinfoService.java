package com.example.demo_aop.service;

import com.example.demo_aop.domain.Stuinfo;

import java.util.List;

/**
 * @ClassName StuinfoService
 * @Description TODO
 * @Author MGG
 * @Date 2020/3/21 21:34
 * @Version 1.0
 */
public interface StuinfoService {

    List<Stuinfo> queryAll();

    int add(Stuinfo stuinfo);
}
