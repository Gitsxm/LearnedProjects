package com.example.demo_aop.controller;

import com.example.demo_aop.domain.Stuinfo;
import com.example.demo_aop.service.StuinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName StuinfoController
 * @Description TODO
 * @Author MGG
 * @Date 2020/3/21 21:40
 * @Version 1.0
 */
@RequestMapping("/stuinfo")
@RestController
public class StuinfoController {

    @Autowired
    private StuinfoService stuService;

    /**
     * 查询
     * @return
     */
    @GetMapping("/list")
    public List<Stuinfo> queryAll() {
        return stuService.queryAll();
    }

    /**
     * 插入
     * @param stuinfo
     * @return
     */
    @PutMapping("/insert")
    public int insert(Stuinfo stuinfo) {
        return stuService.add(stuinfo);
    }
}
