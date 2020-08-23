package com.example.springbootlearning;

import com.example.springbootlearning.domain.Dog;
import com.example.springbootlearning.repository.DogDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootLearningApplicationTests {

    @Autowired
    DogDao dao;
    @Test
    void contextLoads() {
        System.out.println(dao.findADog());

        System.out.println("==查询全部==");
        List list = dao.findAll();
        for (Object obj:list){
            System.out.println(obj);
        }
//        System.out.println("==更改==");
//        System.out.println(dao.updateDog());
//        System.out.println("==删除==");
//        System.out.println(dao.deleteDog());
    }

}
