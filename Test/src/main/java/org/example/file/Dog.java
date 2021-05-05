package org.example.file;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/4 23:23
 */
public class Dog {
    private String name;
    private Integer age;
    //让dog 占用更多空间
    private byte[] bytes = new byte[1024*1024];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
