package org.example;

import org.example.entity.Dog;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 方法引用
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/1 21:51
 */
public class SimpleMethodRef {
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("jj","male",3));
        dogs.add(new Dog("gg","male",2));
        dogs.add(new Dog("hh","male",5));
        dogs.add(new Dog("ss","male",1));

        //匿名方法排序
//        Collections.sort(dogs, new Comparator<Dog>() {
//            @Override
//            public int compare(Dog o1, Dog o2) {
//                return o1.getAge()-o2.getAge();
//            }
//        });

        //改进一： 使用 lambda 表达式
//        Collections.sort(dogs,(Dog o1,Dog o2)->o1.getAge()-o2.getAge());

        //改进二：使用 dog 类中已经确定的比较方法
//        Collections.sort(dogs,(a,b)->Dog.compareAge(a,b));

        //改进三：使用方法引用-类名::静态方法(1)
//        Collections.sort(dogs,Dog::compareAge);

        //也是静态方法引用
//        dogs.forEach(System.out::println);

//        Dog oldestDog = Collections.max(dogs,Dog::compareAge);
//        System.out.println(oldestDog);

        //(2)对象::实例方法的引用 , 通过把一个比较器类实例化，调用它的实例方法
//        DogCompare compare = new DogCompare();
//        Collections.sort(dogs,compare::compareDogByAge);
//        dogs.forEach(System.out::println);
        //那还可以通过一个函数式接口实例化
//        DogCompareInterface compareInterface = (o1,o2)->o1.getAge()-o2.getAge();
//        Collections.sort(dogs,compareInterface::compare);
//        dogs.forEach(System.out::println);
        //(3)类名::实例方法引用
//        Collections.sort(dogs,Dog::compareByAge);
//        dogs.forEach(System.out::println);
        //(4)类名::new 构造引用 提供者，每次get 获得一个新对象。
        Supplier<Dog> supplier = Dog::new;
        System.out.println(supplier.get()==supplier.get());
    }

}
