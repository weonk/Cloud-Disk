package com.chris.service;

import com.chris.pojo.UserFileCustom;
import com.chris.pojo.UserFileQueryVo;

public interface UserFileService {
    public int insertUserFile(UserFileQueryVo userFileQueryVo) throws Exception;
    public int deleteUserFileByUserIdAndFileId(UserFileQueryVo userFileQueryVo) throws Exception;
    public UserFileCustom selectUserFileByUserIdAndFileId(UserFileQueryVo userFileQueryVo) throws Exception;
}
