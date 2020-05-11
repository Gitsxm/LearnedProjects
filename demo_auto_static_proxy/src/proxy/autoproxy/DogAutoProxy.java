package proxy.autoproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName DogAutoProxy
 * @Description Dog 动态代理
 * @Author MGG
 * @Date 2020/4/3 16:57
 * @Version 1.0
 */
public class DogAutoProxy implements InvocationHandler {

    private Object tar; //代理目标  接口一般实现类 如 Dogimpl

    public DogAutoProxy(Object tar){
        this.tar = tar;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("我是动态代理");
        result = method.invoke(tar,args);
        System.out.println("动态代理完毕");
        return result;
    }

}
