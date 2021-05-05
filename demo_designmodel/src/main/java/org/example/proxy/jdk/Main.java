package org.example.proxy.jdk;

import org.example.proxy.HelloService;
import org.example.proxy.HelloServiceImpl;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-06 11:23 PM
 */
public class Main {
    public static void main(String[] args) {
        HelloServiceProxy serviceProxy = new HelloServiceProxy();
        HelloService proxy = (HelloService) serviceProxy.bind(new HelloServiceImpl());
        proxy.sayHello("张三");
    }
}
