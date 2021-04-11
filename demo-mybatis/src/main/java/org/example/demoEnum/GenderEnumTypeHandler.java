package org.example.demoEnum;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TODO 枚举处理器
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-11 10:35 AM
 */
public class GenderEnumTypeHandler implements TypeHandler<Gender> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Gender gender, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,gender.getCode());
    }

    @Override
    public Gender getResult(ResultSet resultSet, String s) throws SQLException {
        int result = resultSet.getInt(s);
        return Gender.getEnumByCode(result);
    }

    @Override
    public Gender getResult(ResultSet resultSet, int i) throws SQLException {
        int result = resultSet.getInt(i);
        return Gender.getEnumByCode(result);
    }

    @Override
    public Gender getResult(CallableStatement callableStatement, int i) throws SQLException {
        int result = callableStatement.getInt(i);
        return Gender.getEnumByCode(result);
    }
}
