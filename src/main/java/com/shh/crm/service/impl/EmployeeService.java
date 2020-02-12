package com.shh.crm.service.impl;

import com.shh.crm.domain.Employee;
import com.shh.crm.domain.Permission;
import com.shh.crm.domain.Role;
import com.shh.crm.mapper.EmployeeMapper;
import com.shh.crm.page.PageResult;
import com.shh.crm.query.EmployeeQueryObject;
import com.shh.crm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Employee record) {
        int effectedCount = employeeMapper.insert(record);
        for (Role role : record.getRoles()) {
            // 给中间表插入数据
            employeeMapper.insertRelation(record.getId(), role.getId());
        }
        return effectedCount;
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Employee record) {
        int effectedCount = employeeMapper.updateByPrimaryKey(record);
        employeeMapper.deleteRelationByEmployeeId(record.getId());
        for (Role role : record.getRoles()) {
            // 给中间表插入数据
            employeeMapper.insertRelation(record.getId(), role.getId());
        }
        return effectedCount;
    }

    @Override
    public Employee getEmployeeForLogin(String username, String password) {
        return employeeMapper.getEmployeeForLogin(username, password);
    }

    @Override
    public PageResult queryForPage(EmployeeQueryObject queryObject) {
        long count = employeeMapper.queryForPageCount(queryObject);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }

        List<Employee> list = employeeMapper.queryForPage(queryObject);
        return new PageResult(count, list);
    }

    @Override
    public int updateState(Long id, Integer state) {
        return employeeMapper.updateState(id, state);
    }
}
