package org.example.Tests;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.example.dao.PersonMapper;
import org.example.entity.Person;

/**
 * TODO 使用代码方式创建 sqlsession
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-05 1:39 PM
 */
public class CodeTest {
    public static void main(String[] args) {
        //构建连接池
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/student?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        //构建事务方式
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        //创建运行环境
        Environment environment = new Environment("development", transactionFactory, dataSource);
        //Configuration 对象
        Configuration configuration = new Configuration(environment);
        //注册一个上下文别名
        configuration.getTypeAliasRegistry().registerAlias("person", Person.class);
        //添加一个映射器,这边要注意映射器 xml文件要与 接口文件再一个目录下，maven工程要配置 resources 将 src 目录下的 xml 也打包进去
        configuration.addMapper(PersonMapper.class);
        //创建 sqlsessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        //使用sqlSession 标准方式
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            //获取映射器
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
//        Person persons = mapper.selectByPrimaryKey(1001);
            //直接通过参数方式查询
            Person persons = sqlSession.selectOne("org.example.dao.PersonMapper.selectByPrimaryKey", 1001);
            if (null != persons) {
                System.out.println(persons.toString());
            }
            //如果是更改操作 则需要提交
            //sqlSession.commit();
        } catch (Exception e) {
            //编辑操作遇到异常要回滚
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
