package com.example.springbootlearning.test;

public class Demo {
    public static void main(String[] args) {
        String id = "120340000";
        char[] chars = id.toCharArray();
        int i = chars.length -1;
        for (;i >= 0; i --){
            if ('0' != chars[i])
                break;
        }
        System.out.println("数字"+chars[i]+"后面都是0，为第"+(i+1)+"位");
    }
}
