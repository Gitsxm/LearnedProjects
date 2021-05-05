package org.example.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * TODO cglib 代理示例
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-07 11:40 PM
 */
public class HelloServiceCglib implements MethodInterceptor {
    private Object target;
    public Object getInstance(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        //回调方法
        enhancer.setSuperclass(this.target.getClass());
        //创建代理对象
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * 回调方法
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB 动态代理......");
        //反射前
        System.out.println("say hello...");
        Object returnObj = methodProxy.invokeSuper(o,objects);
        //反射后
        System.out.println("finished...");
        return returnObj;
    }
}
