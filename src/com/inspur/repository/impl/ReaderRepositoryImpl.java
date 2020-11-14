package com.inspur.repository.impl;

import com.inspur.entity.Reader;
import com.inspur.repository.ReaderRepository;
import com.inspur.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderRepositoryImpl implements ReaderRepository {

    /**
     *  如果成功 返回 是 非空的 reader 对象 如果失败  返回的 是 空的 reader 对象
     * @param username
     * @param password
     * @return
     */
    @Override
    public Reader login(String username, String password) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from reader where username =? and password = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Reader reader = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                reader = new Reader(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return reader;
    }
}
