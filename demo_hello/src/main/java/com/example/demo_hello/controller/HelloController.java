package com.example.demo_hello.controller;

import com.example.demo_hello.dto.PersonDTO;
import com.example.demo_hello.util.PageData;
import com.example.demo_hello.util.RequestUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    /**
     *
     * @param request
     * @return
     */
    @GetMapping("/req")
    public List<PageData> hello(){
        HttpServletRequest request1 = RequestUtil.getRequest();
        PageData pd2 = new PageData(request1);
        List<PageData> list = new ArrayList<>();
        list.add(pd2);
        return list;
    }
}
