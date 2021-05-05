package org.example.jvm.ch3;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/27 15:13
 */
public class SimpleJIT {
    public static void main(String[] args) {
        for (int i=0;i<1501;i++){
            System.out.println(i+print());
        }
    }
    public static String print(){
        return "hello world!";
    }
}
