package com.example.demo_exceptionhandler.controller;

import com.example.demo_exceptionhandler.domain.Dog;
import com.example.demo_exceptionhandler.exceptionhandle.BaseResult;
import com.example.demo_exceptionhandler.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DogController
 * @Description TODO
 * @Author MGG
 * @Date 2020/3/27 17:59
 * @Version 1.0
 */

@RestController
public class DogController {
    @Autowired
    private DogService dogService;

    /**
     * 找狗
     * @param id
     * @return
     */
    @GetMapping("/dog")
    public String findDog(@RequestParam String id){
        Dog dog = dogService.findDog(id);
        if(dog == null)
            return BaseResult.error("未找到这只狗").toString();
        return BaseResult.success(dog).toString();
    }

    /**
     * 模拟业务类中抛出异常
     * @return
     */
    @PostMapping("/exception")
    public String exception(){
        dogService.exceptionTest();
        return BaseResult.success().toString();
    }
}
