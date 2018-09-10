package com.chris.cloud.dao.impl;

import com.chris.cloud.dao.LogDao;
import com.chris.cloud.entity.Log;
import com.chris.cloud.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class LogDaoImpl implements LogDao {
    @Override
    public int insertLog(Log log) throws Exception {
        Connection connection = JdbcUtil.getConnection();
        String sql = "insert into log (use_id, file_index, action, date) value (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<Object> objects = new ArrayList<>();
        objects.add(log.getUserId());
        objects.add(log.getFileIndex());
        objects.add(log.getAction());
        objects.add(log.getData());
        int result = JdbcUtil.doUpdate(preparedStatement, objects);
        JdbcUtil.closeAll(connection, preparedStatement, null);
        return result;
    }
}
