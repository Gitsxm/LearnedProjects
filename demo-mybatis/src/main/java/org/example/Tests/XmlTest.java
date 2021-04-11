package org.example.Tests;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.dao.PersonMapper;
import org.example.entity.Person;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * TODO 加载 XML创建 sqlsession
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-05 1:10 PM
 */
public class XmlTest {
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //使用sqlSession 标准方式
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            //获取映射器
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
//        Person persons = mapper.selectByPrimaryKey(1001);
            //直接通过参数方式查询
            Person persons = sqlSession.selectOne("org.example.dao.PersonMapper.testParam",1001);
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
