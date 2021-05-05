package org.example.jvm.ch1;

import java.util.Vector;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/10/21 23:50
 */
public class DumpOOM {
    public static void main(String[] args) {
        Vector vector = new Vector();
        for (int i=0 ;i<25;i++){
            vector.add(new byte[1*1024*1024]);
        }
    }
}
