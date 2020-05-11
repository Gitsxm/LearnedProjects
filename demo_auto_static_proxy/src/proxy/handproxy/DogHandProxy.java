package proxy.handproxy;

import proxy.Dog;
import proxy.Dogimpl;

/**
 * @ClassName DogHandProxy
 * @Description Dog 静态代理
 * @Author MGG
 * @Date 2020/4/3 16:55
 * @Version 1.0
 */
public class DogHandProxy implements Dog {

    Dogimpl di = new Dogimpl();
    @Override
    public void run() {
        System.out.println(" 我是Dog静态代理 run 方法 ");
        di.run();
    }
}
