package org.example.collect;

import org.example.file.Dog;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO for 循环需不需验证 list 为空集
 *
 * @author MGG
 * @version 1.0
 * @date 2021-03-17 9:30 AM
 */
public class Main1 {
    public static void main(String[] args) {
        List<Dog> list = new ArrayList();
        for (int i=0;i<list.size();i++){
            System.out.println(".......");
        }
        System.out.println(">>>>>>>>>>>>>");
        list = null;
        for (Dog dog: list){
            System.out.println(".......");
        }
    }
}
