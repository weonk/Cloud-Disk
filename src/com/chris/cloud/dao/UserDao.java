package com.chris.cloud.dao;

import com.chris.cloud.entity.User;

public interface UserDao {
    int insertUser(User user) throws Exception;
    int deleteUser(int id) throws Exception;
    int updateUser(User user) throws Exception;
    User getOneUser(int id) throws Exception;
    User getOneUser(String userName, String password) throws Exception;
}
