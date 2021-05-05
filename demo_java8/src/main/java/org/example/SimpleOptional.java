package org.example;

import org.example.entity.Dog;

import java.util.Optional;

/**
 * Optional 类
 *
 * @author MGG
 * @version 1.0
 * @date 2020/10/30 14:38
 */
public class SimpleOptional {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.setName("huahua");
        Dog dog1 = null;
        Optional<Dog> dog2 = Optional.ofNullable(dog);
        Optional<Dog> dog3 = Optional.ofNullable(dog1);
        //使用 of 对象如果为 null 会抛出 NullPointerException
        //Optional<Dog> dog4 = Optional.of(dog1);
        fight(dog2,dog3);
    }

    public static void fight(Optional<Dog> dog1,Optional<Dog> dog2){
        System.out.println("dog1 存在？"+ dog1.isPresent());
        System.out.println("dog2 存在？"+ dog2.isPresent());
        //获取对象
        Dog dog = dog1.get();
        //如果没有值
        Dog dog3 = dog2.orElse(new Dog("guagua","",10));
        System.out.println(dog.getName()+" 与 "+dog3.getName()+" 正在打架！");
    }
}
