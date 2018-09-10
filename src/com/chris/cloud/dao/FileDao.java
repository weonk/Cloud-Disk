package com.chris.cloud.dao;

import com.chris.cloud.entity.FileList;

import java.util.List;

public interface FileDao {
    int insertFile(FileList fileList) throws Exception;
    int deleteFile(int id) throws Exception;
    FileList getOneFileByIndex(int id) throws Exception;
    List<FileList> getFilesByUserId(int userId) throws Exception;
    List<FileList> getFilesByKey(String key) throws Exception;
}
