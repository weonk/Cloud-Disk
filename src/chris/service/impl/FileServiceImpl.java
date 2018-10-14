package com.chris.service.impl;

import com.chris.mapper.FileMapperCustom;
import com.chris.pojo.FileCustom;
import com.chris.pojo.FileQueryVo;
import com.chris.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class FileServiceImpl implements FileService {

    @Qualifier("fileMapperCustom")
    @Autowired
    private FileMapperCustom fileMapperCustom;

    @Override
    public List<FileCustom> selectFileByUser(Integer userId) throws Exception {
        return fileMapperCustom.selectFileByUser(userId);
    }

    @Override
    public int insertFile(FileQueryVo fileQueryVo) throws Exception {
        return fileMapperCustom.insertFile(fileQueryVo);
    }

    @Override
    public FileCustom selectFileByFileName(FileQueryVo fileQueryVo) throws Exception {
        return fileMapperCustom.selectFileByFileName(fileQueryVo);
    }

    @Override
    public FileCustom selectFileById(Integer fileId) throws Exception {
        return fileMapperCustom.selectFileById(fileId);
    }

    @Override
    public List<FileCustom> selectAllFile() throws Exception {
        return fileMapperCustom.selectAllFile();
    }

    @Override
    public List<FileCustom> selectFileByKeyName(FileQueryVo fileQueryVo) throws Exception {
        return fileMapperCustom.selectFileByKeyName(fileQueryVo);
    }
}
