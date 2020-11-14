package com.inspur.repository.impl;

import com.inspur.entity.Book;
import com.inspur.entity.BookCase;
import com.inspur.repository.BookRepository;
import com.inspur.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookerRepositoryImpl implements BookRepository {

    @Override
    public List<Book> findAll(int index, int limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from book,bookcase where book.bookcaseid = bookcase.id limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Book> books = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,limit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                books.add(new Book(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getDouble(6),new BookCase(resultSet.getInt(7),resultSet.getString(8))));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }

        return books;
    }

    @Override
    public int count() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from book,bookcase where book.bookcaseid = bookcase.id";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }
}
