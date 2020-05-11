package com.example.demo_exceptionhandler.service;

import com.example.demo_exceptionhandler.domain.Dog;
import com.example.demo_exceptionhandler.exceptionhandle.BizException;
import com.example.demo_exceptionhandler.exceptionhandle.CommonEnum;
import org.springframework.stereotype.Service;

/**
 * @ClassName DogService
 * @Description TODO
 * @Author MGG
 * @Date 2020/3/27 18:00
 * @Version 1.0
 */
@Service
public class DogService {

    /**
     * 测试自定义异常
     */
    public void exceptionTest(){
        throw new BizException(CommonEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 正常业务
     * @param id
     * @return
     */
    public Dog findDog(String id){
        if(id.equals("1001"))
            return new Dog("kaka",2,id);
        return null;
    }
}
