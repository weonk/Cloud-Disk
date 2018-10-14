package com.chris.service;

import com.chris.pojo.User;
import com.chris.pojo.UserCustom;
import com.chris.pojo.UserQueryVo;

public interface UserService {
    public int insertUser(User user) throws Exception;
    public UserCustom findUserByUserNameAndPassword(UserQueryVo userQueryVo) throws Exception;
    public UserCustom findUserByUserName(UserQueryVo userQueryVo) throws Exception;
    public UserCustom findUserById(UserQueryVo userQueryVo) throws Exception;
    public void UpdateUserInfo(UserQueryVo userQueryVo) throws Exception;
    public void UpdateUserPassword(UserQueryVo userQueryVo) throws Exception;
}
