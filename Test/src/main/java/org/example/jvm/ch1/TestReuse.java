package org.example.jvm.ch1;

public class TestReuse {
    public static void localvar1() {
        int a = 0;
        System.out.println(a);
        int b = 0;
    }

    public static void localvar2() {
        {
            int a = 0;
            System.out.println(a);
        }
        int b = 0;
    }

    public static void main(String[] args) {
        localvar1();
        localvar2();
    }
}