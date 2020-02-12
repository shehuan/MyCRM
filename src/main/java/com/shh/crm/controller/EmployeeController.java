package com.shh.crm.controller;

import com.shh.crm.domain.Employee;
import com.shh.crm.util.Result;
import com.shh.crm.page.PageResult;
import com.shh.crm.query.EmployeeQueryObject;
import com.shh.crm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/page")
    public String page() {
        return "employee";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(EmployeeQueryObject queryObject) {
        return employeeService.queryForPage(queryObject);
    }

    @RequestMapping("/save")
    @ResponseBody
    public Result save(Employee employee) {
        Result result = new Result();
        employee.setPassword("123456");
        employee.setAdmin(0);
        employee.setState(1);
        employee.setUpdateTime(new Date());

        try {
            employeeService.insert(employee);
            result.setMessage("保存成功");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("保存失败，请联系管理员");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(Employee employee) {
        Result result = new Result();
        employee.setUpdateTime(new Date());
        try {
            employeeService.updateByPrimaryKey(employee);
            result.setMessage("更新成功");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("更新失败，请联系管理员");
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
            employeeService.updateState(id, 0);
            result.setMessage("离职成功");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("离职失败，请联系管理员");
        }
        return result;
    }
}
