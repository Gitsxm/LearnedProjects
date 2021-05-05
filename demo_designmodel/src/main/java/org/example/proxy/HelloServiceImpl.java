package org.example.proxy;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-06 11:12 PM
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
