package com.shh.crm.service;

import com.shh.crm.domain.*;
import com.shh.crm.mapper.PermissionMapper;
import com.shh.crm.page.PageResult;
import com.shh.crm.query.EmployeeQueryObject;
import com.shh.crm.query.PermissionQueryObject;
import com.shh.crm.query.RoleQueryObject;
import com.shh.crm.util.PermissionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestService {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private ILogService logService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleService roleService;


    @Test
    public void employeeService() {
        EmployeeQueryObject queryObject = new EmployeeQueryObject();
        queryObject.setPage(1);
        queryObject.setRows(10);
        PageResult employee = employeeService.queryForPage(queryObject);
        System.out.println(employee);
    }

    @Test
    public void departmentService() {
        List<Department> list = departmentService.queryForEmployee();
        System.out.println(list);
    }

    @Test
    public void logService() {
        List<Log> list = logService.selectAll();
        System.out.println(list);
    }

    @Test
    public void permissionService() {
//        PermissionQueryObject queryObject = new PermissionQueryObject();
//        queryObject.setPage(1);
//        queryObject.setRows(10);
//        PageResult result = permissionService.queryForPage(queryObject);
//        System.out.println(result);

        List<Permission> list = permissionService.queryPermissionByEmployeeId(1L);
        System.out.println(list);
    }

    @Test
    public void roleService() {
        List<Role> list = roleService.selectAll();
        System.out.println(list);
    }
}
