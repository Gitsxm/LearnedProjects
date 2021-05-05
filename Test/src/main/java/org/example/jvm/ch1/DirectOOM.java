package org.example.jvm.ch1;

import java.nio.ByteBuffer;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/19 23:28
 */
public class DirectOOM {
    public static void main(String[] args) {
        for (int i=0;i<1000000000;i++){
            ByteBuffer.allocateDirect(1024*1024*1024);
            System.out.println(i);
        }
    }
}
