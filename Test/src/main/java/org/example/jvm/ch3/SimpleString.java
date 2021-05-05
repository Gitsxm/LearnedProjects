package org.example.jvm.ch3;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/24 23:11
 */
public class SimpleString {
    public static void main(String[] args) {
        String str1 = "abcd";
    }

    public void test(String ...strs){
        String str = "";
        for (String str1:strs
             ) {
            str+=str1;
        }
    }
}
