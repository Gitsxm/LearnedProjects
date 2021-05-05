package org.example.clone;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021-03-15 12:23 AM
 */
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person();
        // 浅克隆
        Person p2 = (Person) p1.clone();
        System.out.println(p1 == p2);
        // p2的name与p1的name 指向同一个堆地址 并没有新建一个引用地址
        System.out.println(p2.getName() == p1.getName());
        Person p3 = CloneUtils.clone(p1);
        System.out.println(p1 == p3);
        // 通过流操作p3的name指向了一个新的对地址
        System.out.println(p3.getName() == p1.getName());
    }
}
