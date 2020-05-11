package com.example.demo02.service.impl;

import com.example.demo02.domain.Jgjwd;
import com.example.demo02.domain.Jgjwd0;
import com.example.demo02.repository.JgjwdMapper;
import com.example.demo02.service.JgjwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName JgjwdServiceImpl
 * @Description TODO
 * @Author MGG
 * @Date 2019/1/17 0017 19:06
 * @Version 1.0
 */
@Service
public class JgjwdServiceImpl implements JgjwdService {
    @Autowired
    private JgjwdMapper jg;

    @Override
    public List<Jgjwd0> findALl() {
        List<Jgjwd> list = jg.findAll();
        List<Jgjwd0> list2 = new ArrayList<>();
        for (Jgjwd jgjwd : list) {
            list2.add(new Jgjwd0(new BigDecimal(jgjwd.getLng()), new BigDecimal(jgjwd.getLat()), jgjwd.getCount()));
        }
        return list2;
    }
}
