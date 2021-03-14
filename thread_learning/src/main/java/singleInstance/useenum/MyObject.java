package singleInstance.useenum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * TODO 单例模式，使用枚举类利用和惊天块相似的特性。
 *
 * @author MGG
 * @version 1.0
 * @date 2021/2/9 11:19
 */
public class MyObject {
    public enum MyEnumSingleton {
        connectionFactory;
        private Connection connection;

        private MyEnumSingleton() {
            System.out.println("调用了 MyObject 构造。。");
            try {
                String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8&characterEncoding=utf-8";
                String USERNAME = "root";
                String PASSWORD = "1234";
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public Connection getConnection() {
            return connection;
        }
    }

    public static Connection getConnection() {
        return MyEnumSingleton.connectionFactory.getConnection();
    }
}
