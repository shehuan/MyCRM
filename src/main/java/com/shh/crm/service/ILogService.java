package com.shh.crm.service;

import com.shh.crm.domain.Log;

import java.util.List;

public interface ILogService {
    int insert(Log record);

    List<Log> selectAll();
}
