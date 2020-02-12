package com.shh.crm.service.impl;

import com.shh.crm.domain.Department;
import com.shh.crm.mapper.DepartmentMapper;
import com.shh.crm.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Department record) {
        return 0;
    }

    @Override
    public Department selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<Department> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Department record) {
        return 0;
    }

    @Override
    public List<Department> queryForEmployee() {
        return departmentMapper.queryForEmployee();
    }
}
