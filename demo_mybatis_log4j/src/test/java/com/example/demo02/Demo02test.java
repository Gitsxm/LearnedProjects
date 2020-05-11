package com.example.demo02;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @ClassName demo02test
 * @Description 反射测试用
 * @Author MGG
 * @Date 2019/1/28 0028 15:11
 * @Version 1.0
 */
public class Demo02test {
    public static void main(String[] args) {
        try {
            Class cls = Class.forName("com.example.demo02.domain.People");
            Constructor peopleConstructor = cls.getConstructor();
            Object obj = peopleConstructor.newInstance();
            Method setAgeMethod = cls.getMethod("setAge", int.class);
            setAgeMethod.invoke(obj, 20);
            Method getAgeMethod = cls.getMethod("getAge");
            System.out.println(getAgeMethod.invoke(obj));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
