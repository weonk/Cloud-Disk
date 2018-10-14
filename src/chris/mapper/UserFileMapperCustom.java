package com.chris.mapper;

import com.chris.pojo.UserFileCustom;
import com.chris.pojo.UserFileQueryVo;

public interface UserFileMapperCustom {
    public int insertUserFile(UserFileQueryVo userFileQueryVo) throws Exception;
    public int deleteUserFileByUserIdAndFileId(UserFileQueryVo userFileQueryVo) throws Exception;
    public UserFileCustom selectUserFileByUserIdAndFileId(UserFileQueryVo userFileQueryVo) throws Exception;
}
