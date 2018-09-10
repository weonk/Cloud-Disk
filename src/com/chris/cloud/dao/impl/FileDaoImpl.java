package com.chris.cloud.dao.impl;

import com.chris.cloud.dao.FileDao;
import com.chris.cloud.entity.FileList;
import com.chris.cloud.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FileDaoImpl implements FileDao {
    @Override
    public int insertFile(FileList fileList) throws Exception {
        Connection connection = JdbcUtil.getConnection();
        String sql = "insert into filelist (user_id, name, src, size, date) value (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<Object> objects = new ArrayList<>();
        objects.add(fileList.getUserId());
        objects.add(fileList.getName());
        objects.add(fileList.getSrc());
        objects.add(fileList.getSize());
        objects.add(fileList.getDate());
        int result = JdbcUtil.doUpdate(preparedStatement, objects);
        JdbcUtil.closeAll(connection, preparedStatement, null);
        return result;
    }

    @Override
    public int deleteFile(int id) throws Exception {
        Connection connection = JdbcUtil.getConnection();
        String sql = "delete from filelist where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<Object> objects = new ArrayList<>();
        objects.add(id);
        int result = JdbcUtil.doUpdate(preparedStatement, objects);
        JdbcUtil.closeAll(connection, preparedStatement, null);
        return result;
    }

    @Override
    public FileList getOneFileByIndex(int id) throws Exception {
        Connection connection = JdbcUtil.getConnection();
        String sql = "select * from filelist where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<Object> objects = new ArrayList<>();
        objects.add(id);
        ResultSet resultSet = JdbcUtil.doQuery(preparedStatement, objects);
        FileList fileList = null;
        if (resultSet.next()) {
            fileList = new FileList();
            doResultSet(resultSet, fileList);
        }
        return fileList;
    }

    @Override
    public List<FileList> getFilesByUserId(int userId) throws Exception {
        Connection connection = JdbcUtil.getConnection();
        String sql = "select * from filelist where user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<Object> objects = new ArrayList<>();
        objects.add(userId);
        ResultSet resultSet = JdbcUtil.doQuery(preparedStatement, objects);
        List<FileList> fileLists = new ArrayList<>();

        while (resultSet.next()) {
            FileList fileList = new FileList();
            doResultSet(resultSet, fileList);
            fileLists.add(fileList);
        }

        if (fileLists.size() == 0) {
            return null;
        } else {
            return fileLists;
        }
    }

    @Override
    public List<FileList> getFilesByKey(String key) throws Exception {
        Connection connection = JdbcUtil.getConnection();
        String sql = "select * from filelist where name like '%%" + key + "%%'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = JdbcUtil.doQuery(preparedStatement, null);
        List<FileList> fileLists = new ArrayList<>();

        while (resultSet.next()) {
            FileList fileList = new FileList();
            doResultSet(resultSet, fileList);
            fileLists.add(fileList);
        }

        if (fileLists.size() == 0) {
            return null;
        } else {
            return fileLists;
        }
    }

    private void doResultSet(ResultSet resultSet, FileList fileList) throws Exception {
        fileList.setId(resultSet.getInt("id"));
        fileList.setUserId(resultSet.getInt("user_id"));
        fileList.setName(resultSet.getString("name"));
        fileList.setSrc(resultSet.getString("src"));
        fileList.setSize(resultSet.getString("size"));
        fileList.setDate(resultSet.getString("date"));
    }
}
