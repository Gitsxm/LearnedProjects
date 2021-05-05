package org.example.jvm.ch1;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/19 23:11
 */

public class SimpleOOM {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        for (int i = 1; i < 1000000000; i++) {
            list.add(new byte[1024 * 1024]);
        }
    }
}