package proxy.autoproxy;

import proxy.Dog;
import proxy.Dogimpl;

import java.lang.reflect.Proxy;

/**
 * @ClassName Test
 * @Description TODO
 * @Author MGG
 * @Date 2020/4/3 17:46
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        //加上这句将会产生一个$Proxy0.class文件，这个文件即为动态生成的代理类文件
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        // 实现类实例
        Dogimpl dogimpl = new Dogimpl();
        // 动态代理类实例
        DogAutoProxy dogAutoProxy = new DogAutoProxy(dogimpl);
        Dog dog = (Dog) Proxy.newProxyInstance(DogAutoProxy.class.getClassLoader(),dogimpl.getClass().getInterfaces(),dogAutoProxy);
        dog.run();

    }
}
