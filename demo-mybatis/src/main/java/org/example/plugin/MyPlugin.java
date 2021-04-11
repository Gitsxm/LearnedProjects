package org.example.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * TODO 插件实例-查看调用顺序
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-09 2:48 PM
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class MyPlugin implements Interceptor {
    Properties props = null;

    /**
     * 代替拦截对象方法的内容
     *
     * @param invocation 责任链对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("before...");
        //非代理对象就不会到这一步了，代理对象到这。
        Object obj = invocation.proceed();
        System.out.println("after...");
        return obj;
    }

    /**
     * 重写代理对象生成方法，这里使用 Plugin.wrap 因为接口有默认实现如果不需要对代理对象做改动，这里可以不重写
     *
     * @param target 被代理对象
     * @return
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("调用生成代理对象...");
        return Plugin.wrap(target, this);
    }

    /**
     * 参数设置，如果没有参数要设置，因为接口有默认实现空方法，也可以不重写
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println(properties.get("dbType"));
        this.props = properties;
    }
}
