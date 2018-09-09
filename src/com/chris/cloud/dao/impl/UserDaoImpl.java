package com.chris.cloud.dao.impl;

import com.chris.cloud.dao.UserDao;
import com.chris.cloud.entity.User;
import com.chris.cloud.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public int insertUser(User user) throws Exception {
        Connection connection = JdbcUtil.getConnection();
        String sql = "insert into user (username, password) value (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<Object> objects = new ArrayList<>();
        objects.add(user.getUserName());
        objects.add(user.getPassword());
        int result = JdbcUtil.doUpdate(preparedStatement, objects);
        JdbcUtil.closeAll(connection, preparedStatement, null);
        return result;
    }

    @Override
    public int deleteUser(int id) throws Exception {
        Connection connection = JdbcUtil.getConnection();
        String sql = "delete from user where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<Object> objects = new ArrayList<>();
        objects.add(id);
        int result = JdbcUtil.doUpdate(preparedStatement, objects);
        JdbcUtil.closeAll(connection, preparedStatement, null);
        return result;
    }

    @Override
    public int updateUser(User user) throws Exception {
        Connection connection = JdbcUtil.getConnection();
        String sql = "update user set username = ? and password = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<Object> objects = new ArrayList<>();
        objects.add(user.getUserName());
        objects.add(user.getPassword());
        objects.add(user.getId());
        int result = JdbcUtil.doUpdate(preparedStatement, objects);
        JdbcUtil.closeAll(connection, preparedStatement, null);
        return result;
    }

    @Override
    public User getOneUser(int id) throws Exception {
        Connection connection = JdbcUtil.getConnection();
        String sql = "select * from user where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<Object> objects = new ArrayList<>();
        objects.add(id);
        ResultSet resultSet = JdbcUtil.doQuery(preparedStatement, objects);
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUserName(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
        }
        return user;
    }

    @Override
    public User getOneUser(String userName, String password) throws Exception {
        Connection connection = JdbcUtil.getConnection();
        String sql = "select * from user where username = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<Object> objects = new ArrayList<>();
        objects.add(userName);
        objects.add(password);
        ResultSet resultSet = JdbcUtil.doQuery(preparedStatement, objects);
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUserName(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
        }
        return user;
    }
}
