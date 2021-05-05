package org.example.jvm.ch1;

public class LocalvarGC {
    public void localvarGc1() {
        byte[] a = new byte[6 * 1024 * 1024];//6M
        System.gc();
    }

    public void localvarGc2() {
        byte[] a = new byte[6 * 1024 * 1024];
        a = null;
        System.gc();
    }

    public void localvarGc3() {
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        System.gc();
    }

    public void localvarGc4() {
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        int c = 10;
        System.gc();
    }

    public void localvarGc5() {
        localvarGc1();
        System.gc();
    }

    public static void main(String[] args) {
        LocalvarGC ins = new LocalvarGC();
        System.out.println("----");
        ins.localvarGc1();
        System.out.println("----");
        ins.localvarGc2();
        System.out.println("----");
        ins.localvarGc3();
        System.out.println("----");
        ins.localvarGc4();
        System.out.println("----");
        ins.localvarGc5();
    }
}