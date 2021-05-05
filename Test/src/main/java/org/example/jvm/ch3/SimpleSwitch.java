package org.example.jvm.ch3;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/25 9:59
 */
public class SimpleSwitch {
    public static void main(String[] args) {
        int i = 10;
        int sum = 0;
        switch (i) {
            case 1:
                sum = 1;
                break;
            case 3:
                sum = 3;
                break;
            case 9:
                sum = 9;
                break;
            case 10:
                sum = 10;
                break;
            case 30:
                sum = 30;
                break;
            default: sum =0;
        }
    }
}
