package com.shh.crm.mapper;

import com.shh.crm.domain.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogMapper {

    int insert(Log record);

    List<Log> selectAll();
}