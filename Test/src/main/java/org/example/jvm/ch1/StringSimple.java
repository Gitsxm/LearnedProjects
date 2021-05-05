package org.example.jvm.ch1;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/21 22:37
 */
public class StringSimple {
    public static void main(String[] args) {
        String str1 = new String("123");
        String str2 = new String("123");
        System.out.println(str1==str2);
        System.out.println(str1==str2.intern());
        System.out.println("123"==str2.intern());
        System.out.println(str1.intern()==str2.intern());
        String.valueOf(new Object());
    }
}
