package com.chris.service.impl;

import com.chris.mapper.UserFileMapperCustom;
import com.chris.pojo.UserFileCustom;
import com.chris.pojo.UserFileQueryVo;
import com.chris.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserFileServiceImpl implements UserFileService {

    @Qualifier("userFileMapperCustom")
    @Autowired
    private UserFileMapperCustom userFileMapperCustom;

    @Override
    public int insertUserFile(UserFileQueryVo userFileQueryVo) throws Exception {
        return userFileMapperCustom.insertUserFile(userFileQueryVo);
    }

    @Override
    public int deleteUserFileByUserIdAndFileId(UserFileQueryVo userFileQueryVo) throws Exception {
        return userFileMapperCustom.deleteUserFileByUserIdAndFileId(userFileQueryVo);
    }

    @Override
    public UserFileCustom selectUserFileByUserIdAndFileId(UserFileQueryVo userFileQueryVo) throws Exception {
        return userFileMapperCustom.selectUserFileByUserIdAndFileId(userFileQueryVo);
    }
}
