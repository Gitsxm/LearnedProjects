package org.example.jvm.ch1;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2020/10/20 23:43
 */
public class HeapAlloc {
    public static void main(String[] args) {
        System.out.print("maxMemory=");
        System.out.println(Runtime.getRuntime().maxMemory()+" bytes");
        System.out.print("freeMemory=");
        System.out.println(Runtime.getRuntime().freeMemory()+" bytes");
        System.out.print("totalMemory=");
        System.out.println(Runtime.getRuntime().totalMemory()+" bytes");

        byte[] bytes = new byte[1024*1024*1];
        System.out.println("分配1M空间给数组!");

        System.out.print("maxMemory=");
        System.out.println(Runtime.getRuntime().maxMemory()+" bytes");
        System.out.print("freeMemory=");
        System.out.println(Runtime.getRuntime().freeMemory()+" bytes");
        System.out.print("totalMemory=");
        System.out.println(Runtime.getRuntime().totalMemory()+" bytes");

        byte[] bytes2 = new byte[1024*1024*4];
        System.out.println("分配4M空间给数组!");

        System.out.print("maxMemory=");
        System.out.println(Runtime.getRuntime().maxMemory()+" bytes");
        System.out.print("freeMemory=");
        System.out.println(Runtime.getRuntime().freeMemory()+" bytes");
        System.out.print("totalMemory=");
        System.out.println(Runtime.getRuntime().totalMemory()+" bytes");
    }
}
