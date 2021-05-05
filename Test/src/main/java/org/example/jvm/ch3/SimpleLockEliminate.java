package org.example.jvm.ch3;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 锁消除示例
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/23 16:33
 */
public class SimpleLockEliminate {
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        for (int i=0;i<10000000;i++){
            stringAdd("girl and ","boy");
        }
        System.out.println(System.currentTimeMillis()-begin);
    }

    public static String stringAdd(String source,String params){
        StringBuffer str = new StringBuffer();
        return str.append(source).append(params).toString();
    }

    public static void concurrentmap(){
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("",1);
        map.size();
        AtomicInteger integer = new AtomicInteger(1);
        integer.getAndAdd(1);
    }

}
