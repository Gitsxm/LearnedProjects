package maptoobj.entities;

/**
 * @ClassName Person
 * @Description TODO
 * @Author MGG
 * @Date 2020/3/15 23:45
 * @Version 1.0
 */
public class Person {
    private String name;
    private int age;
    private String gender;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
