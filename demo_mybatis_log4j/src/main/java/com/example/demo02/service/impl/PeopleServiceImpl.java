package com.example.demo02.service.impl;/**
 * @ClassName PeopleServiceImpl
 * @Description TODO
 * @Author MGG
 * @Date 2018/12/22 0022 12:11
 * @Version 1.0
 */

import com.example.demo02.domain.People;
import com.example.demo02.repository.PeopleMapper;
import com.example.demo02.service.PeopleService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName PeopleServiceImpl
 * @Description TODO
 * @Author MGG
 * @Date 2018/12/22 0022 12:11
 * @Version 1.0
 */
@Service
public class PeopleServiceImpl implements PeopleService {
    @Resource
    private PeopleMapper dao;
    //方法使用    @Transactional注解  启用注解支持

    /**
     * @return
     */
    public List<People> findAll() {
        return dao.findAll();
    }

    public List<People> findAllById(String id) {
        return dao.selectByPrimaryKey(id);
    }

    /**
     * 测试插入事务控制
     *
     * @param people
     * @return
     */
    @Transactional
    public int insertIntoTable(People people) {
        int i = dao.insert(people);
        if (true) {
            throw new RuntimeException("测试事务.");
        }
        return i;
    }
}
