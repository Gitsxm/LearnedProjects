package org.example.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-06 11:14 PM
 */
public class HelloServiceProxy implements InvocationHandler {
    /**
     * 真实服务对象
     */
    private Object target;

    /**
     * 绑定委托对象并返回一个代理类
     * @param target
     * @return
     */
    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this); //JDK 动态代理提供接口
    }

    /**
     * 通过代理对象调用方法
     * @param proxy 代理对象
     * @param method 被调用方法
     * @param args 方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK 动态代理......");
        Object result = null;
        System.out.println("say hello......");
        result = method.invoke(target,args);
        System.out.println("finished....");
        return result;
    }
}
