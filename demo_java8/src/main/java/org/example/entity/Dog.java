package org.example.entity;

public class Dog {
    private String name;
    private String gender;
    private int age;

    public Dog(){
        super();
    }
    public Dog(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }

    /**
     * 已经确定的静态比较方法
     * @param dog1
     * @param dog2
     * @return
     */
    public static int compareAge(Dog dog1,Dog dog2){
        return dog1.getAge()-dog2.getAge();
    }

    /**
     * 实例方法比较
     * @param dog
     * @param dog1
     * @return
     */
    public int compareName(Dog dog,Dog dog1){
        return dog.getName().compareTo(dog1.getName());
    }

    /**
     * 这种才是合适的实例方法，本实例和其他实例比较。上面两种有两个参数比较的方法，其实放在任何比较类中都可以。
     * @param dog
     * @return
     */
    public int compareByAge(Dog dog){
        return this.getAge()-dog.getAge();
    }
}
