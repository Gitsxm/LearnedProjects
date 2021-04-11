package org.example.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * TODO SqlSessionFactory 单例静态，创建 SqlSession 。
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-05 3:48 PM
 */
public class SqlSessionFactoryUtil {
    /**
     * SqlSessionFactory 对象
     */
    private static SqlSessionFactory sqlSessionFactory = null;
    /**
     * 类线程锁
     */
    private static final Class CLASS_LOCK = SqlSessionFactoryUtil.class;

    /**
     * 私有化构造参数 避免 new 创建多对象。
     */
    private SqlSessionFactoryUtil() {
    }

    /**
     * 创建 SqlSessionFactoryUtil
     *
     * @return
     */
    public static SqlSessionFactory initSqlSessionFactory() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            LoggerFactory.getLogger(SqlSessionFactoryUtil.class).error("创建 SqlSessionFactory 错误{}", e.getMessage());
        }
        synchronized (CLASS_LOCK) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        return sqlSessionFactory;
    }

    /**
     * 获取 SqlSession
     * 保证系统中同时只有一个 factory
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static SqlSession openSqlSession() {
        if (sqlSessionFactory == null) {
            initSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }
}
