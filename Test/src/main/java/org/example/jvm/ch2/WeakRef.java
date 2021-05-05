package org.example.jvm.ch2;

import org.example.file.Dog;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/5 23:51
 */
public class WeakRef {
    public static void main(String[] args) {
        Dog dog = new Dog("huahua", 2);
        WeakReference<Dog> ref = new WeakReference<>(dog);
        dog = null;
        System.out.println(ref.get());
        System.gc();
        System.out.println("After gc");
        System.out.println(ref.get());

    }
}
