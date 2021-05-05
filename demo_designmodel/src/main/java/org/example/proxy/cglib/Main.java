package org.example.proxy.cglib;

import org.example.proxy.HelloServiceImpl;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-07 11:48 PM
 */
public class Main {
    public static void main(String[] args) {
        HelloServiceCglib helloServiceCglib = new HelloServiceCglib();
        HelloServiceImpl cglibProxy = (HelloServiceImpl) helloServiceCglib.getInstance(new HelloServiceImpl());
        cglibProxy.sayHello("张三");
    }
}
