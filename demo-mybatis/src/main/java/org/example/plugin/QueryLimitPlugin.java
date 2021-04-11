package org.example.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;

/**
 * TODO 限制返回行数插件
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-09 3:47 PM
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class QueryLimitPlugin implements Interceptor {
    /**
     * 默认限制查询返回行数
     */
    private int limit;
    /**
     * 数据库类型
     */
    private String dbType;

    private static final String LIMIT_TABLE_NAME = "limit_table_name_person";

    /**
     * 代理对象拦截方法，在这里处理 sql
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 取被拦截对象
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        //分离代理对象，从而形成多次代理，通过两次循环得到最原始的代理类
        while (metaObject.hasGetter("h")) {
            Object o = metaObject.getValue("h");
            metaObject = SystemMetaObject.forObject(o);
        }
        //分离最后一个代理对象的目标类
        while (metaObject.hasGetter("target")) {
            Object o = metaObject.getValue("target");
            metaObject = SystemMetaObject.forObject(o);
        }
        //取出要执行的 sql
        String sql = (String) metaObject.getValue("delegate.boundSql.sql");
        String limitSql;
        //判断参数是不是 Mysql 数据库且 sql 没有被插件重写过
        if ("mysql".equals(this.dbType) && sql.indexOf(LIMIT_TABLE_NAME) == -1) {
            //去掉前后空格
            sql = sql.trim();
            limitSql = "select * from (" + sql + ") " + LIMIT_TABLE_NAME + " limit " + limit;
            //重设要执行的 sql
            metaObject.setValue("delegate.boundSql.sql", limitSql);
        }
        //调用原来对象的方法，进入责任链的下一层级
        return invocation.proceed();
    }

    /**
     * 生成代理对象方法，如不做修改，可以不重写，
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 参数设置
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        String strLimit = properties.getProperty("limit", "50");
        this.limit = Integer.parseInt(strLimit);
        this.dbType = properties.getProperty("dbType", "mysql");
    }
}
