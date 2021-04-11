package org.example.plugin.pageplugin;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-11 8:47 PM
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class PagingPlugin implements Interceptor {
    /**
     * 默认页码
     */
    private Integer defaultPage;
    /**
     * 默认每页大小
     */
    private Integer defaultPageSize;
    /**
     * 默认是否启动插件
     */
    private Boolean defaultUseFlag;
    /**
     * 默认是否检查当前页码正确性
     */
    private Boolean defaultCheckFlag;

    /**
     * 分页算法
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = getUnProxyObject(invocation);
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        String sql = (String) metaObject.getValue("delegate.boundSql.sql");
        //不是查询不分页
        if (!checkSelect(sql)) {
            return invocation.proceed();
        }
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        Object paramterObj = boundSql.getParameterObject();
        PageParams pageParams = getPageParams(paramterObj);
        //没有分页参数不分页
        if (null == pageParams) {
            return invocation.proceed();
        }
        //分页参数未正确设置使用默认
        Integer pageNum = pageParams.getPage() == null ? this.defaultPage : pageParams.getPage();
        Integer pageSize = pageParams.getPageSize() == null ? this.defaultPage : pageParams.getPageSize();
        Boolean useFlag = pageParams.getUseFlag() == null ? this.defaultUseFlag : pageParams.getUseFlag();
        Boolean checkFalg = pageParams.getCheckFlag() == null ? this.defaultCheckFlag : pageParams.getCheckFlag();
        //是否分页
        if (!useFlag) {
            return invocation.proceed();
        }
        int total = getTotal(invocation, metaObject, boundSql);
        setToTotalPageParams(pageParams, total, pageSize);
        checkPage(checkFalg,pageNum,pageParams.getTotalPage());
        return changeSql(invocation,metaObject,boundSql,pageNum,pageSize);
    }

    /**
     * 改变原 sql 添加分页代码 mysql 版本，其他版本要改变逻辑
     * @param invocation
     * @param metaObject
     * @param boundSql
     * @param pageNum
     * @param pageSize
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    private Object changeSql(Invocation invocation, MetaObject metaObject, BoundSql boundSql, Integer pageNum, Integer pageSize) throws InvocationTargetException, IllegalAccessException, SQLException {
        String sql = (String) metaObject.getValue("delegate.boundSql.sql");
        //对应的 mysql 数据库语法，不一样的要修改
        String newSql = "select * from ("+sql+") $_paging_table limit ?,?";
        metaObject.setValue("delegate.boundSql.sql",newSql);
        //相当于调用 StatementHandler.prepare 方法，因为我们拦截的就是这个方法，最后肯定是要调用的，预编译了sql并设置了参数，但是缺少两个分页参数。
        PreparedStatement statement = (PreparedStatement) invocation.proceed();
        //计算 sql 总参数个数
        int count = statement.getParameterMetaData().getParameterCount();
        //sql 占位符替换为参数
        statement.setInt(count-1,(pageNum-1)*pageSize);
        statement.setInt(count,pageSize);
        return statement;
    }

    /**
     * 检查参数
     * @param checkFalg
     * @param pageNum
     * @param totalPage
     */
    private void checkPage(Boolean checkFalg, Integer pageNum, Integer totalPage) {
        if (checkFalg){
            if (pageNum > totalPage){
                throw new IllegalArgumentException("查询失败，查询页码["+pageNum+"]大于总页数["+totalPage+"]!!!");
            }
        }
    }

    /**
     * 设置分页参数,总条数，总页数
     * @param pageParams
     * @param total
     * @param pageSize
     */
    private void setToTotalPageParams(PageParams pageParams, int total, Integer pageSize) {
        pageParams.setTotal(total);
        //计算总数
        int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        pageParams.setTotalPage(totalPage);
    }

    /**
     * 查询 sql 的所有条数
     *
     * @param invocation
     * @param metaObject
     * @param boundSql
     * @return
     * @throws SQLException
     */
    private int getTotal(Invocation invocation, MetaObject metaObject, BoundSql boundSql) throws SQLException {
        //获取当前 MappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        //获取当前 Configuration
        Configuration cfg = mappedStatement.getConfiguration();
        //获取当前 sql
        String sql = (String) metaObject.getValue("delegate.boundSql.sql");
        //创建当前 sql 的总条数 sql
        String countSql = "select count(*) as total from (" + sql + ")$_paging";
        //获取链接，我们知道 conn 参数在参数表的第一个
        Connection connection = (Connection) invocation.getArgs()[0];
        PreparedStatement preparedStatement = null;
        int total = 0;
        try {
            //预编译总数 sql
            preparedStatement = connection.prepareStatement(countSql);
            //构建总数 BoundSql
            BoundSql countBoundSql = new BoundSql(cfg, countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            //构建总数 ParameterHandler
            ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBoundSql);
            //填充参数
            parameterHandler.setParameters(preparedStatement);
            //查询总数
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != preparedStatement) {
                preparedStatement.close();
            }
        }
        System.out.println("Total: " + total);
        return total;
    }

    /**
     * 获取参数
     * 可以使用 Map 传递，可以使用 @Param 注解，可以使用 POJO 继承 PageParams
     *
     * @param paramterObj
     * @return
     */
    private PageParams getPageParams(Object paramterObj) {
        if (null == paramterObj) {
            return null;
        }
        PageParams pageParams = null;
        if (paramterObj instanceof Map) {
            Map<String, Object> paramMap = (Map<String, Object>) paramterObj;
            Set<String> keySet = paramMap.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                Object value = paramMap.get(key);
                if (value instanceof PageParams) {
                    return (PageParams) value;
                }
            }
        } else if (paramterObj instanceof PageParams) {
            pageParams = (PageParams) paramterObj;
        }
        return pageParams;

    }

    /**
     * 检查是否 select 语句
     *
     * @param sql
     * @return
     */
    private boolean checkSelect(String sql) {
        String trimSql = sql.trim();
        int index = trimSql.toLowerCase().indexOf("select");
        return index == 0;
    }

    /**
     * 分离代理的最原始对象
     *
     * @param invocation
     * @return
     */
    private StatementHandler getUnProxyObject(Invocation invocation) {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        Object object = null;
        //循环责任链，获取最原始对象
        while (metaObject.hasGetter("h")) {
            object = metaObject.getValue("h");
        }
        if (null == object) {
            return statementHandler;
        }
        return (StatementHandler) object;
    }

    /**
     * 参数读取与设置
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        String strDefaultPage = properties.getProperty("default.page", "1");
        String strDefaultPageSize = properties.getProperty("default.pageSize", "10");
        String strDefaultUseFlag = properties.getProperty("default.useFlag", "false");
        String strDefaultCheckFlag = properties.getProperty("default.checkFlag", "false");
        this.defaultPage = Integer.parseInt(strDefaultPage);
        this.defaultPageSize = Integer.parseInt(strDefaultPageSize);
        this.defaultUseFlag = Boolean.parseBoolean(strDefaultUseFlag);
        this.defaultCheckFlag = Boolean.parseBoolean(strDefaultCheckFlag);
    }
}
