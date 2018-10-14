package com.chris.service.impl;

import com.chris.mapper.UserMapper;
import com.chris.mapper.UserMapperCustom;
import com.chris.pojo.User;
import com.chris.pojo.UserCustom;
import com.chris.pojo.UserQueryVo;
import com.chris.service.UserService;
import com.chris.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMapperCustom userMapperCustom;

    @Override
    public int insertUser(User user) throws Exception {
        user.setTime(new Date());
        user.setPassword(MD5.md5(user.getPassword()));
        return userMapper.insert(user);
    }

    @Override
    public UserCustom findUserByUserNameAndPassword(UserQueryVo userQueryVo) throws Exception {
        userQueryVo.getUserCustom().setPassword(MD5.md5(userQueryVo.getUserCustom().getPassword()));
        return userMapperCustom.selectUserByUserNameAndPwd(userQueryVo);
    }

    @Override
    public UserCustom findUserByUserName(UserQueryVo userQueryVo) throws Exception {
        return userMapperCustom.selectUserByUserName(userQueryVo);
    }

    @Override
    public UserCustom findUserById(UserQueryVo userQueryVo) throws Exception {
        return userMapperCustom.selectUserById(userQueryVo);
    }

    @Override
    public void UpdateUserInfo(UserQueryVo userQueryVo) throws Exception {
        userMapperCustom.updateUserInfo(userQueryVo);
    }

    @Override
    public void UpdateUserPassword(UserQueryVo userQueryVo) throws Exception {
        userMapperCustom.updateUserPassword(userQueryVo);
    }
}
