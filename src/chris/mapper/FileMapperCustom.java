package com.chris.mapper;

import com.chris.pojo.FileCustom;
import com.chris.pojo.FileQueryVo;

import java.util.List;

public interface FileMapperCustom {
    public List<FileCustom> selectFileByUser(Integer userId) throws Exception;
    public int insertFile(FileQueryVo fileQueryVo) throws Exception;
    public FileCustom selectFileByFileName(FileQueryVo fileQueryVo) throws Exception;
    public FileCustom selectFileById(Integer fileId) throws Exception;
    public List<FileCustom> selectAllFile() throws Exception;
    public List<FileCustom> selectFileByKeyName(FileQueryVo fileQueryVo) throws Exception;
}
