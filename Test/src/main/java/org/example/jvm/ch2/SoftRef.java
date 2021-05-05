package org.example.jvm.ch2;

import org.example.file.Dog;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/4 23:22
 */
public class SoftRef {
    public static void main(String[] args) {
        List<SoftReference> ulist = new ArrayList<>();
        int i = 10;
        while (i > 0) {
            System.out.println("Now look at what we get from ref:");
            ulist.add(new SoftReference(new Dog("huahua", 1)));//一直new新的Dog对象，导致内存不足触发GC
//            ulist.forEach(ref -> System.out.println(ref.get()));  //不出意外，list元素会越来越多，但是get到的基本都是null
            i--;
        }
    }
}
