package com.chris.cloud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class JdbcUtil {
    private static String jdbcDriver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://xxx:3306/cloud";
    private static String user = "xxx";
    private static String password = "xxx";

    public static Connection getConnection() throws Exception {
        Class.forName(jdbcDriver);
        return DriverManager.getConnection(url, user, password);
    }

    public static ResultSet doQuery(PreparedStatement preparedStatement, List<Object> objects) throws Exception{
        doPreparedStatement(objects, preparedStatement);
        return preparedStatement.executeQuery();
    }

    public static int doUpdate(PreparedStatement preparedStatement, List<Object> objects) throws Exception {
        doPreparedStatement(objects, preparedStatement);
        return preparedStatement.executeUpdate();
    }

    public static void doPreparedStatement(List<Object> objects, PreparedStatement preparedStatement) throws Exception {
        if (objects != null && objects.size() > 0) {
            int count = 1;
            for (Object o: objects
            ) {
                preparedStatement.setObject(count, o);
                count++;
            }
        }
    }

    public static void closeAll(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws Exception {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
