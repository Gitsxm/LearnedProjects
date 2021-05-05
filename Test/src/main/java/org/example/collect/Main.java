package org.example.collect;

import org.example.file.Dog;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO 分类规约
 *
 * @author MGG
 * @version 1.0
 * @date 2021-03-16 3:58 PM
 */
public class Main {
    public static void main(String[] args) {
        List<Dog> dogList = new ArrayList<>(10);
        Dog d1 = new Dog("a", 1);
        Dog d2 = new Dog("a", 2);
        Dog d3 = new Dog("a", 3);
        Dog d7 = new Dog("a", 1);
        Dog d4 = new Dog("b", 2);
        Dog d6 = new Dog("b", 2);
        Dog d5 = new Dog("b", 3);
        Dog d8 = new Dog("c", 4);
        Dog d9 = new Dog("c", 2);
        Dog d10 = new Dog("c", 2);
        dogList.add(d8);
        dogList.add(d9);
        dogList.add(d10);
        dogList.add(d1);
        dogList.add(d2);
        dogList.add(d3);
        dogList.add(d4);
        dogList.add(d5);
        dogList.add(d6);
        dogList.add(d7);
        Map<String, List<Dog>> dogmap = dogList.stream().collect(Collectors.groupingBy(Dog::getName));
        Map<String, List<Dog>> dogmapSort = dogList.stream().collect(Collectors.groupingBy(Dog::getName, LinkedHashMap::new, Collectors.toList()));
        dogmap.keySet().forEach(e -> {
            System.out.println("mapKey: " + e);
            dogmap.get(e).forEach(dog -> {
                System.out.println(dog);
            });
        });
        System.out.println("下面的 key 保持了原 list 的顺序");
        dogmapSort.keySet().forEach(e -> {
            System.out.println("mapKey: " + e);
            dogmap.get(e).forEach(dog -> {
                System.out.println(dog);
            });
        });

    }
}
