package org.example.jvm.ch2;

import java.lang.ref.Reference;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/11/4 22:40
 */
public class CanReliveObj {
    public static CanReliveObj canReliveObj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanReliveObj finalize called");
        canReliveObj = this;
    }

    @Override
    public String toString() {
        return "I am CanReliveObj";
    }

    public static void main(String[] args) throws InterruptedException {
        canReliveObj = new CanReliveObj();
        canReliveObj = null;
        System.gc();
        Thread.sleep(1000);
        if (null == canReliveObj){
            System.out.println("canReliveObj is null");
        }else {
            System.out.println("canReliveObj is not null");
        }
        System.out.println("one more gc......");
        canReliveObj = null;
        System.gc();
        Thread.sleep(1000);
        if (null == canReliveObj){
            System.out.println("canReliveObj is null");
        }else {
            System.out.println("canReliveObj is not null");
        }
    }
}
