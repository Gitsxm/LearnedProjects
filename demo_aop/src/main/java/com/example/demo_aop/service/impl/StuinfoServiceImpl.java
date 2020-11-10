package com.example.demo_aop.service.impl;

import com.example.demo_aop.domain.Stuinfo;
import com.example.demo_aop.repository.StuinfoMapper;
import com.example.demo_aop.service.StuinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName StuinfoServiceImpl
 * @Description TODO
 * @Author MGG
 * @Date 2020/3/21 21:35
 * @Version 1.0
 */
@Service
public class StuinfoServiceImpl implements StuinfoService {
    @Autowired
    private StuinfoMapper stuinfoMapper;

    /**
     * 查询所有
     *
     * @return
     */
    public List<Stuinfo> queryAll() {
        return stuinfoMapper.selectAll();
    }

    /**
     * 插入
     *
     * @param stuinfo
     * @return
     */
    @Override
    public int add(Stuinfo stuinfo) {
        int i = stuinfoMapper.insert(stuinfo);
        return i;
    }
}
