package org.example.jvm.ch3;

import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 偏向锁测试
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/23 9:31
 */
public class SimpleLockBiased {
    public static List<Integer> numberList = new Vector<>();

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        int count = 0;
        int startnum = 0;
        while(count<1000000){
            numberList.add(startnum);
            startnum+=2;
            count++;
        }
        System.out.println(System.currentTimeMillis()-begin);
    }
}
