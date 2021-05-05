package org.example.template;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-26 10:52 PM
 */
public class Main {
    public static void main(String[] args) {
        AbstractClass c1 = new Class1();
        AbstractClass c2 = new Class2();
        c1.templateMethod();
        c2.templateMethod();
    }
}
