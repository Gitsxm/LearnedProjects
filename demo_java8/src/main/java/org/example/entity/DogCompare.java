package org.example.entity;

/**
 * 狗比较器类
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/1 22:45
 */
public class DogCompare {
    public int compareDogByAge(Dog o1,Dog o2){
        return o1.getAge()-o2.getAge();
    }
}
