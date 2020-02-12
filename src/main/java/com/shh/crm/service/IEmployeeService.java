package com.shh.crm.service;

import com.shh.crm.domain.Employee;
import com.shh.crm.page.PageResult;
import com.shh.crm.query.EmployeeQueryObject;

import java.util.List;

public interface IEmployeeService {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Employee getEmployeeForLogin(String username, String password);

    PageResult queryForPage(EmployeeQueryObject queryObject);

    int updateState(Long id, Integer state);
}
