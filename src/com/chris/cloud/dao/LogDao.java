package com.chris.cloud.dao;

import com.chris.cloud.entity.Log;

public interface LogDao {
    int insertLog(Log log) throws Exception;
}
