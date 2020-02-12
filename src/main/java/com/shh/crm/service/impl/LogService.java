package com.shh.crm.service.impl;

import com.shh.crm.domain.Log;
import com.shh.crm.mapper.LogMapper;
import com.shh.crm.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService implements ILogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public int insert(Log record) {
        return logMapper.insert(record);
    }

    @Override
    public List<Log> selectAll() {
        return logMapper.selectAll();
    }
}
