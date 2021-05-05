package org.example.jvm.ch3;

/**
 * 方法内联
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/27 15:56
 */
public class SimpleInLine {
    static int i = 0;

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        for (int j = 0; j < 10000000; j++) {
            addI();
        }
        System.out.println(System.currentTimeMillis() - begin);
    }

    public static void addI() {
        i++;
    }
}
