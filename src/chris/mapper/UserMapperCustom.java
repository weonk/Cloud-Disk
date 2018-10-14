package com.chris.mapper;

import com.chris.pojo.UserCustom;
import com.chris.pojo.UserQueryVo;

public interface UserMapperCustom {
    UserCustom selectUserByUserNameAndPwd(UserQueryVo userQueryVo);

    UserCustom selectUserByUserName(UserQueryVo userQueryVo);

    UserCustom selectUserById(UserQueryVo userQueryVo);

    void updateUserInfo(UserQueryVo userQueryVo);

    void updateUserPassword(UserQueryVo userQueryVo);
}
