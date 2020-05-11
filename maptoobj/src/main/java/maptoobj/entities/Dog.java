package maptoobj.entities;

/**
 * @ClassName Dog
 * @Description TODO
 * @Author MGG
 * @Date 2020/3/15 23:14
 * @Version 1.0
 */
public class Dog {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
