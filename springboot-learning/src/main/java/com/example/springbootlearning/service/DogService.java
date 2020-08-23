package com.example.springbootlearning.service;

import com.example.springbootlearning.domain.Dog;
import com.example.springbootlearning.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DogService {
    @Autowired
    private DogRepository dao;

    @Transactional(rollbackFor = IllegalArgumentException.class )
    public void addWithRollBack(Dog dog){
        dao.save(dog);
        if(dog.getName().equals("huahua")){
            throw new IllegalArgumentException("事务回滚");
        }

    }

    @Transactional(noRollbackFor =  IllegalArgumentException.class )
    public void addWithOutRollBack(Dog dog){
        dao.save(dog);
        if(dog.getName().equals("huahua")){
            throw new IllegalArgumentException("无事务回滚");
        }
    }
}
